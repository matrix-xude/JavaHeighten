package com.xxd.thread.pool;

import java.util.List;
import java.util.concurrent.*;

public class PoolTest {

    public static void main(String[] args) {
        PoolTest poolTest = new PoolTest();
//        poolTest.createThreadSequence();
        poolTest.shutdownTest();
//        poolTest.createPool();
    }

    private void createPool(){
        new ThreadPoolExecutor(
                3, // 核心线程数，优先开启（当核心线程没满时，新任务优先开启新线程）
                6, // 最大线程数，会在blockingQueue满之后开启,非核心线程数 （maxPoolSize - corePoolSize）
                60, // 非核心线程无任务时保持的最长时间
                TimeUnit.SECONDS, // 上一个参数的单位
                new LinkedBlockingQueue<Runnable>(),  // 使用的缓存队列
                (ThreadFactory) r -> new Thread(r),  // 创建线程的工厂，可以自己创建线程（给线程命名啥的）
                new ThreadPoolExecutor.AbortPolicy()  // 拒绝策略，当前最大线程满，并且队列满时。继续接受任务会被调用
        );
    }

    private int threadNumber = 0;

    /**
     * 创建线程的顺序
     */
    private void createThreadSequence() {
        ExecutorService executorService = Executors.newFixedThreadPool(3, r -> new Thread(r, String.format("线程%d --", threadNumber++)));

        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                System.out.printf("%s执行了\r\n", Thread.currentThread().getName());
            });
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // 正在执行的：继续执行，直到完成
        // 不再接受新任务，如果此时你再调用 execute()，会触发拒绝策略
        executorService.shutdown();
        // isShutdown()在shutdown()被调用后，会立马返回true
        System.out.printf("%s isShutdown()=%s\r\n", Thread.currentThread().getName(), executorService.isShutdown());
    }

    /**
     * 测试 isShutdown() 与 isTerminated()
     */
    private void shutdownTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(3, r -> new Thread(r, String.format("线程%d --", threadNumber++)));

        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                System.out.printf("%s执行了\r\n", Thread.currentThread().getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
                    e.printStackTrace();
                }
            });
        }

        // 正在执行的：尝试发送 interrupt() 中断信号
        // 队列中排队的：全部丢弃，不再执行
        // 返回值可以拿到丢弃的队列中的任务
        List<Runnable> runnables = executorService.shutdownNow();
        // isShutdown()在shutdown()被调用后，会立马返回true
        System.out.printf("%s isShutdown()=%s\r\n", Thread.currentThread().getName(), executorService.isShutdown());
        // isTerminated()在线程池所有task执行完之后才会返回true
        System.out.printf("%s isTerminated()=%s\r\n", Thread.currentThread().getName(), executorService.isTerminated());
    }
}
