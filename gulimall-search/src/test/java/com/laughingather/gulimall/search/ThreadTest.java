package com.laughingather.gulimall.search;

import lombok.extern.slf4j.Slf4j;

/**
 * 多线程测试类
 *
 * @author：laughingather
 * @create：2021-06-01 23:00
 */
@Slf4j
public class ThreadTest {

    /**
     * 实现多线程
     * 1、继承Thread类
     * 2、实现Runnable接口
     *
     * @param args
     */
    public static void main(String[] args) {
        log.info("main...start...");

        // 1实现方式
        // MyThread myThread = new MyThread();
        // myThread.start();

        // 2实现方式
        MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable).start();

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

}
