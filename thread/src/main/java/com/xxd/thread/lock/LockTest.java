package com.xxd.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    private final Lock mLock = new ReentrantLock();
    private final Condition mCondition = mLock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        LockTest lockTest = new LockTest();
//        lockTest.mThread1.start();
        lockTest.mThread2.start();

        Thread.sleep(200);
        lockTest.mThread2.interrupt();

    }

    /**
     * 为什么 lock.lock() 要写在 try 外面？ 因为如果 lock.lock() 本身在获取锁的过程中失败（虽然概率极低，比如发生 Error），
     * 它还没拿到锁。如果它在 try 块内，finally 就会尝试去 unlock() 一个没拿到的锁，从而再次抛出 IllegalMonitorStateException，掩盖了原始的错误。
     */
    private final Thread mThread1 = new Thread(() -> {
        // 为了彻底避免unLock报出IllegalMonitorStateException，请务必遵守 “拿到锁后紧跟 try-finally” 的金律：
        mLock.lock();
        try {
            System.out.println("Thread1拿到Lock");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mLock.unlock();
        }
    });

    /**
     * lockInterruptibly() 是 Lock 接口中非常重要的一个方法。它与 lock() 的区别在于：
     * 如果线程在等待获取锁的过程中被中断（调用了 interrupt()），lock() 会继续死等，
     * 而 lockInterruptibly() 会直接抛出 InterruptedException，允许线程立即响应中断，避免死等导致的“僵尸线程”。
     */
    private final Thread mThread2 = new Thread(() -> {
//        mLock.lock();
        try {
            // 1. 如果锁获取失败（抛出 InterruptedException），线程并没有持有锁。
            // 2. 如果此时在 finally 中调用 unlock()，就会报错。
            // 3. 准则：只有成功获取锁后，才进入 try-finally 块。
            mLock.lockInterruptibly(); // 此方法会抛出异常，可能先于 mThread1 打印，具体看interrupt()时间
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            Thread.sleep(100);
            System.out.println("Thread2拿到Lock");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mLock.unlock();
        }
    });
}
