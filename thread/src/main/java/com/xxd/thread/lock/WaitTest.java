package com.xxd.thread.lock;

public class WaitTest {

    public static void main(String[] args) {
        WaitTest waitTest = new WaitTest();
        waitTest.thread1.start();
        waitTest.thread2.start();
    }

    private final Object object = new Object();// 琐

    private final Thread thread1 = new Thread(() -> {
        synchronized (object) {
            System.out.printf("%s 被执行到了\r\n", Thread.currentThread().getName());
            while (true) {
                try {
                    System.out.printf("%s while(true)循环中……\r\n",Thread.currentThread().getName());
                    object.wait();  // 这里会释放当前线程的琐，thread2可以拿到琐了
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }, "线程1");


    private final Thread thread2 = new Thread(() -> {
        synchronized (object) {
            System.out.printf("%s 被执行到了\r\n", Thread.currentThread().getName());
            try {
                object.notifyAll();  // 这里唤醒了thread1，进入就绪状态，但是不释放琐
                System.out.printf("%s while(true)循环中……\r\n", Thread.currentThread().getName());
                object.wait();  // 这里才释放琐
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }, "线程2");
}
