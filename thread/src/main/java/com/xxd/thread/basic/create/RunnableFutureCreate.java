package com.xxd.thread.basic.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 方式 2 ：实现 Runnable 同时实现 Future
 * 可完成返回值的获取，内部原始是 for 循环一直等待
 */
public class RunnableFutureCreate extends FutureTask<String> {

    public static void main(String[] args) {

        // 创建一个 Runnable，Future
        RunnableFutureCreate future = new RunnableFutureCreate(() -> "我是Callable");

        // 开启线程
        Thread thread = new Thread(future);
        thread.start();

        // 获取返回值
        try {
            String s = future.get();
            System.out.println(s);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public RunnableFutureCreate(Callable<String> callable) {
        super(callable);
    }
}
