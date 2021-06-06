package com.xxd.thread.basic.create;

/**
 * 方式 2 ：实现 Runnable
 */
public class RunnableCreate implements Runnable{

    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableCreate());
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("通过实现 Runnable 接口实现");
    }
}
