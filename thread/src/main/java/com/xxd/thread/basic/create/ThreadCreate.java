package com.xxd.thread.basic.create;

/**
 * 方式 1 ：继承Thread
 */
public class ThreadCreate extends Thread {

    public static void main(String[] args) {
        Thread thread = new ThreadCreate();
        thread.start();
        // thread.start(); //  重复开启会抛出 IllegalThreadStateException
    }

    @Override
    public void run() {
//        try {
//            this.join(); // join到自己会卡死，无限等待
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("通过继承Thread ,覆写run()实现");
    }
}
