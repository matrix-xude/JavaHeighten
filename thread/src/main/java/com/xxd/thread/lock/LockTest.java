package com.xxd.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    private final Lock mLock = new ReentrantLock();
    private final Condition mCondition = mLock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        LockTest lockTest = new LockTest();
        lockTest.mThread2.start();
        lockTest.mThread1.start();

//        Thread.sleep(200);
        lockTest.mThread2.interrupt();

    }

    private final Thread mThread1 = new Thread(() -> {
        try {
            mLock.lock();
            Thread.sleep(2000);
            System.out.println("Thread1拿到Lock");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mLock.unlock();
        }
    });

    private final Thread mThread2 = new Thread(() -> {
        try {
            mLock.lockInterruptibly(); // 此方法会抛出异常，可能先于 mThread1 打印，具体看interrupt()时间
            System.out.println("Thread2拿到Lock");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mLock.unlock();
        }
    });
}
