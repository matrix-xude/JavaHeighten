package com.xxd.thread.basic.join;

public class ThreadJoin {

    public static void main(String[] args) {
        ThreadJoin threadJoin = new ThreadJoin();
        threadJoin.t2.start();
        threadJoin.t1.start();
    }

    private Thread t1 = new Thread(()->{
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("%s执行到了结束阶段\r\n",Thread.currentThread().getName());
    },"线程1");

    private Thread t2 = new Thread(()->{
        try {
            t1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("%s执行到了结束阶段\r\n",Thread.currentThread().getName());
    },"线程2");
}
