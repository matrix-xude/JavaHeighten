package com.xxd.thread.basic.interrupt;


/**
 * 测试interrupt在主线程中的方法
 */
public class InterruptWhile {

    public static void main(String[] args) throws InterruptedException {
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
        System.out.printf("主线程中，isInterrupted=%s\r\n",thread.isInterrupted());
        System.out.printf("主线程中，isInterrupted=%s\r\n",thread.isInterrupted());
        System.out.printf("主线程中，isInterrupted=%s\r\n",thread.isInterrupted());
        System.out.printf("主线程中，isInterrupted=%s\r\n",thread.isInterrupted());
        System.out.printf("主线程中，isInterrupted=%s\r\n",thread.isInterrupted());
    }

    private static Thread thread = new Thread(() -> {

        while (!Thread.currentThread().isInterrupted()){
            System.out.printf("我正在循环中，isInterrupted=%s\r\n",Thread.currentThread().isInterrupted());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.printf("异常捕获，isInterrupted=%s",Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt();  // 这行不加，会继续循环
            }
        }
        System.out.printf("出while循环，isInterrupted=%s\r\n",Thread.currentThread().isInterrupted());
        System.out.printf("出while循环，isInterrupted=%s\r\n",Thread.currentThread().isInterrupted());
        System.out.printf("出while循环，isInterrupted=%s\r\n",Thread.currentThread().isInterrupted());
        System.out.printf("出while循环，isInterrupted=%s\r\n",Thread.currentThread().isInterrupted());
//        while (true){
//
//        }
    });
}
