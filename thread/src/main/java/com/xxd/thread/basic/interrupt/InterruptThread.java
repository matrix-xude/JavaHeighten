package com.xxd.thread.basic.interrupt;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 中断线程方式 2 ： 使用 interrupt()
 * thread.interrupt()  在线程中打入一个中断标志，并不会马上中断线程，当线程被阻塞的时候（如：sleep(),wait(),join()），马上会抛出一个 InterruptedException,且中断标志被清除，线程中断。
 */
public class InterruptThread {

    private final FutureTask<Integer> futureTask = new FutureTask<>(() -> {
        Thread.sleep(1000);
        System.out.println("我是sleep之后的");
        return 1;
    });

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        InterruptThread instance = new InterruptThread();

        Thread thread = new Thread(instance.futureTask);
        thread.start();
        thread.interrupt();
        Thread.sleep(500);  // 这等待是为了让thread清除中断标记

        // 中断标志判断false，是因为阻塞被检测到了，清除了中断标记
        System.out.printf("当前线程是否被interrupt -> %s\n", thread.isInterrupted());

        System.out.printf("当前线程是否被取消 -> %s\n", instance.futureTask.isCancelled());
        System.out.printf("当前线程是否结束 -> %s\n", instance.futureTask.isDone());
        System.out.printf("当前i的数值是 -> %s\n", instance.futureTask.get()); //使用FutureTask 在interrupt()之后调用get()方法才会抛异常

    }

}
