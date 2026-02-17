package com.xxd.thread.byte_test;

public class ByteSyn {

    private Object o = new Object();

    public synchronized int add(int a, int b) {
        return a + b;
    }

    public int minus(int a, int b) {
        synchronized (o) {
            return a - b;
        }
    }
}
