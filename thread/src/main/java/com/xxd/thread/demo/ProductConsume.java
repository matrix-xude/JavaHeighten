package com.xxd.thread.demo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 一个生产消费者模式的例子
 */
public class ProductConsume {

    public static void main(String[] args) {
        ProductConsume instance = new ProductConsume();
        instance.mConsumeThread.start();
        instance.mProductThread.start();
    }

    // 需要不同线程公用的资源
    private final List<String> mList = new LinkedList<>();
    // 最大存储数量
    private static final int MAX_COUNT = 3;

    private Thread mProductThread = new Thread() {
        @Override
        public void run() {
            int index = 0;
            while (true) {
                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (mList) {
                    try {
                        while (mList.size() == MAX_COUNT) {
                            System.out.println("当前队列已经满了：" + Arrays.toString(mList.toArray()));
                            mList.wait();
                        }
                        String productName = String.format("特斯拉%s号", index++);
                        mList.add(productName);
                        System.out.printf("生产出了一个产品，名字 -> %s\n", productName);
                        mList.notifyAll();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    private Thread mConsumeThread = new Thread() {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (mList) {
                    try {
                        while (mList.isEmpty()) {
                            System.out.println("当前队列为0");
                            mList.wait();
                        }
                        String remove = mList.remove(0);
                        System.out.printf("消费了，名字: %s\n", remove);
                        mList.notifyAll();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };
}
