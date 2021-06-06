package com.xxd.thread.basic.interrupt;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 中断线程方式 2 ： 使用 interrupt()
 * thread.interrupt()  在线程中打入一个中断标志，并不会马上中断线程，当线程被阻塞的时候（如：sleep(),wait(),join()），马上会抛出一个 InterruptedException,且中断标志被清除，线程中断。
 * 如要加入逻辑操作，thread.isInterrupted 可以判断中断标志是否为true,当前中断判断后立马回变为false，二次判断则为true
 */
public class InterruptThread {

    private final FutureTask<Integer> futureTask = new FutureTask<>(() -> {
        Thread.sleep(1000);
        System.out.println("我是sleep之后的");
        return 1;
    });

    private final Thread mThread = new Thread(() -> {
        try {
            wait();
            System.out.println("我是wait（）之后的");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    });

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        InterruptThread instance = new InterruptThread();

        Thread thread = new Thread(instance.futureTask);
        thread.start();
        thread.interrupt();

        // 2次中断标志判断结果不同，中断进行一次判断后会被重置
        System.out.printf("当前线程是否被interrupt -> %s\n", thread.isInterrupted());
        System.out.printf("当前线程是否被interrupt -> %s\n", thread.isInterrupted());

        System.out.printf("当前线程是否被取消 -> %s\n", instance.futureTask.isCancelled());
        System.out.printf("当前线程是否结束 -> %s\n", instance.futureTask.isDone());
        System.out.printf("当前i的数值是 -> %s\n", instance.futureTask.get()); //使用FutureTask 在interrupt()之后调用get()方法才会抛异常


        instance.mThread.start();
        instance.mThread.interrupt();  // 有sleep() 直接抛异常

    }


}
