package com.xxd.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {

    public static void main(String[] args) throws InterruptedException {
        AtomicTest atomicTest = new AtomicTest();
        atomicTest.thread1.start();
        atomicTest.thread2.start();
        Thread.sleep(100);
        // 多次运行结果都是2000，保证了线程安全
        System.out.printf("atomicInteger=%d", atomicTest.atomicInteger.intValue());
    }

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private final Thread thread1 = new Thread(() -> {
        int i = 0;
        while (i < 1000) {
            boolean b = atomicInteger.compareAndSet(atomicInteger.intValue(), atomicInteger.intValue() + 1);
            i = b ? i + 1 : i;
        }
    }, "线程1");


    private final Thread thread2 = new Thread(() -> {
        int i = 0;
        while (i < 1000) {
            boolean b = atomicInteger.compareAndSet(atomicInteger.intValue(), atomicInteger.intValue() + 1);
            i = b ? i + 1 : i;
        }
    }, "线程2");
}
