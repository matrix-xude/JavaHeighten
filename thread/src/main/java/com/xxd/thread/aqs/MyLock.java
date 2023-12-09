package com.xxd.thread.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyLock implements Lock {

    private SimpleAQS simpleAQS = new SimpleAQS();

    public static class SimpleAQS extends AbstractQueuedSynchronizer {

        @Override
        protected boolean tryAcquire(int arg) {
            // 直接拿琐，不判断
            boolean b = compareAndSetState(0, arg);
            if (b) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (!isHeldExclusively())  // 当前线程没拿到琐，调用释放琐抛异常，与ReentrantLock类似
                throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getExclusiveOwnerThread() == Thread.currentThread();
        }

    }

    // demo只实现lock，unlock方法
    @Override
    public void lock() {
        // 注意这里调用的是acquire()方法，而不是tryAcquire(),否则会报错
        simpleAQS.acquire(1);
    }

    @Override
    public void unlock() {
        simpleAQS.release(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }



    @Override
    public Condition newCondition() {
        return null;
    }
}
