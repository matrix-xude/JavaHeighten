package com.xxd.thread.basic.interrupt;

import java.util.concurrent.locks.Lock;

/**
 * 中断死锁线程是否可行？
 * 结论：interrupt() 无法中断死锁
 */
public class InterruptDeadlock {

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        InterruptDeadlock instance = new InterruptDeadlock();
        // 形成死锁
        instance.mThread1.start();
        instance.mThread2.start();

        // 让2个Thread的sleep走完
        Thread.sleep(500);
//        instance.mThread1.stop();
        instance.mThread1.interrupt(); // 无法中断死锁
    }


    private Thread mThread1 = new Thread(() -> {
        synchronized (lock1) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lock2) {
                System.out.printf("mThread1 拿到了2把锁了");
            }
        }
    });

    private Thread mThread2 = new Thread(() -> {
        synchronized (lock2) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lock1) {
                System.out.printf("mThread2 拿到了2把锁了");
            }
        }
    });

}
