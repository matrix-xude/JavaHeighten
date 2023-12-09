package com.xxd.thread.pool;

import java.util.concurrent.*;

public class BlockingQueueTest {

    public static void main(String[] args) {
        BlockingQueueTest blockingQueueTest = new BlockingQueueTest();
        blockingQueueTest.testQueue();
    }


    private void createQueue(){
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        // 方式1：抛出异常
        queue.add("a");
        queue.remove();
        String element = queue.element();// 获取队列头，但是不移除
        // 方式2：返回特殊值
        queue.offer("b");
        queue.poll();
        String peek = queue.peek(); // null when queue is empty
        // 方式3：一直阻塞
        try {
            queue.put("c");
            queue.take();
            // 无检查方法
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 方式4：超时退出
        try {
            queue.offer("d",2,TimeUnit.SECONDS);
            queue.poll(2,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void testQueue(){
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        new Thread(()->{
            for (int i = 0; i < 4; i++) {
                try {
                    queue.put(String.format("数据%d",i+1));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(()->{
            try {
                String take = queue.take();
                System.out.printf("element=%s\r\n",take);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }


}
