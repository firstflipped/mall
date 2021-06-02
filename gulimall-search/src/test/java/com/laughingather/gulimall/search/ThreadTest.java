package com.laughingather.gulimall.search;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 多线程测试类
 *
 * @author：laughingather
 * @create：2021-06-01 23:00
 */
@Slf4j
public class ThreadTest {

    /**
     * 线程池一般在一个应用中只应该存在少个，每个异步任务提交给线程池，让他自己执行就行
     */
    public static ExecutorService executorService = Executors.newFixedThreadPool(10);

    /**
     * 多线程没法使用@Test进行测试
     * <p>
     * 实现多线程
     * 1、继承Thread类
     * 2、实现Runnable接口
     * 3、实现Callable接口 + FutureTask（可以拿到返回结果，可以处理异常）
     * <p>
     * 以后在业务代码里面，以上三种启动线程的方式都不用
     * <p>
     * 4、线程池，给线程池直接提交任务
     * 1）Executors.newFixedThreadPool
     * 2）new ThreadPoolExecutor()
     * <p>
     * 区别：
     * 1、2不能得到返回值。3可以获取返回值
     * 1、2、3都不能控制资源
     * 4可以控制资源，性能稳定
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        log.info("main...start...");

        // 1实现方式
        // MyThread myThread = new MyThread();
        // myThread.start();

        // 2实现方式
        // MyRunnable myRunnable = new MyRunnable();
        // new Thread(myRunnable).start();

        // 3实现方式
        // FutureTask<Integer> integerFutureTask = new FutureTask<>(new MyCallable());
        // new Thread(integerFutureTask).start();
        // 阻塞等待：可以等待线程执行完成，拿到返回结果
        // Integer i = integerFutureTask.get();
        // log.info("main...end..." + i);

        // 4.1 实现方式
        // executorService.execute(new MyRunnable());


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,
                200,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        log.info("main...end...");
    }

    public static class MyThread extends Thread {
        @Override
        public void run() {
            log.info("当前线程:{}", Thread.currentThread().getId());
            int i = 10 / 2;
            log.info("运行结果:{}", i);
        }
    }

    public static class MyRunnable implements Runnable {
        @Override
        public void run() {
            log.info("当前线程:{}", Thread.currentThread().getId());
            int i = 10 / 2;
            log.info("运行结果:{}", i);
        }
    }

    public static class MyCallable implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            log.info("当前线程:{}", Thread.currentThread().getId());
            int i = 10 / 2;
            log.info("运行结果:{}", i);
            return i;
        }
    }


}
