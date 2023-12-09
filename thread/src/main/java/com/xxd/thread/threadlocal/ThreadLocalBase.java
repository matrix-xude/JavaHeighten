package com.xxd.thread.threadlocal;

public class ThreadLocalBase {

    // 需要初始化，否则set之前get值为null
    private ThreadLocal<String> threadLocal1 = new ThreadLocal<>(){
        @Override
        protected String initialValue() {
            return super.initialValue();
        }
    };
    private ThreadLocal<String> threadLocal2 = new ThreadLocal<>();


    public static void main(String[] args) throws InterruptedException {
        ThreadLocalBase base = new ThreadLocalBase();
        base.threadLocal1.set(Thread.currentThread().getName());
        base.thread1.start();
        base.thread2.start();
        Thread.sleep(100);
        System.out.println(base.threadLocal1.get());  // 打印main，其它线程改变与本线程无关

        // remove可以避免内存泄漏
        base.threadLocal1.remove();
        System.out.println(base.threadLocal1.get());  // 打印main，其它线程改变与本线程无关
    }

    private final Thread thread1 = new Thread(()->{
        threadLocal1.set(Thread.currentThread().getName());
    });

    private final Thread thread2 = new Thread(()->{
        threadLocal1.set(Thread.currentThread().getName());
    });

}
