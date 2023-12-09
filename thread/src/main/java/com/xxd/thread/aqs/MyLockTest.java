package com.xxd.thread.aqs;

public class MyLockTest {

    public static void main(String[] args) {
        MyLockTest test = new MyLockTest();
        test.t1.start();
        test.t2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("number=%d", test.number);
    }

    private final MyLock myLock = new MyLock();
    private int number = 0;

    private final Thread t1 = new Thread(() -> {
        for (int i = 0; i < 10000; i++) {
            myLock.lock();
            number++;
            myLock.unlock();
        }
    });

    private final Thread t2 = new Thread(() -> {
        for (int i = 0; i < 10000; i++) {
            myLock.lock();
            number++;
            myLock.unlock();
        }
    });
}
