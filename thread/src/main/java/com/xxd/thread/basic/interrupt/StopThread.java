package com.xxd.thread.basic.interrupt;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 中断线程方式 1 ： 使用 stop() 等过时API
 * 暴力停止，已经过时，数据可能出问题，各种异常
 * 比如此例子：stop()导致回调不会发生，程序一直等待
 */
public class StopThread {

    private final FutureTask<Integer> futureTask = new FutureTask<>(() -> {
        Thread.sleep(1000);
        System.out.println("我是sleep之后的");
        return 1;
    });

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        StopThread instance = new StopThread();

        Thread thread = new Thread(instance.futureTask);
        thread.start();
        thread.stop();

        System.out.printf("当前线程是否被取消 -> %s\n", instance.futureTask.isCancelled());
        System.out.printf("当前线程是否结束 -> %s\n", instance.futureTask.isDone());
        System.out.printf("当前i的数值是 -> %s\n", instance.futureTask.get()); // stop()暴力结束线程导致返回一直等待


    }


}
