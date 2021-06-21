package com.laughingather.gulimall.search;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步编排测试
 *
 * @author：laughingather
 * @create：2021-06-02 21:31
 */

@Slf4j
public class CompletableFutureTest {
    public static ExecutorService executorService = Executors.newFixedThreadPool(10);


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        log.info("start.........");

        // 不需要获取返回值
//        CompletableFuture.runAsync(() -> {
//            log.info("当前线程:{}", Thread.currentThread().getId());
//            int i = 10 / 2;
//            log.info("运行结果:{}", i);
//        }, executorService);


//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//            log.info("当前线程:{}", Thread.currentThread().getId());
//            int i = 10 / 2;
//            log.info("运行结果:{}", i);
//            return i;
//        }, executorService);
//        log.info("stop.........{}", future.get());

//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//            log.info("当前线程:{}", Thread.currentThread().getId());
//            int i = 10 / 0;
//            log.info("运行结果:{}", i);
//            return i;
//        }, executorService).whenComplete((result, exception) -> {
//            // 虽然可以得到异常，但是没法修改返回数据
//            log.info("异步任务执行完成，结果是{}，异常是{}", result, exception);
//        }).exceptionally(exception -> 10);
//        Integer integer = future.get();
//        log.info("stop.........{}", integer);

//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//            log.info("当前线程:{}", Thread.currentThread().getId());
//            int i = 10 / 4;
//            log.info("运行结果:{}", i);
//            return i;
//        }, executorService).handle((result, exception) -> {
//            if (Objects.nonNull(result)) {
//                return result * 2;
//            }
//            if (Objects.nonNull(exception)) {
//                return 0;
//            }
//            return 0;
//        });
//        Integer integer = future.get();
//        log.info("stop.........{}", integer);


        CompletableFuture.supplyAsync(() -> {
            log.info("当前线程:{}", Thread.currentThread().getId());
            int i = 10 / 4;
            log.info("运行结果:{}", i);
            return i;
        }, executorService).thenAcceptAsync(result -> {
            log.info("任务2启动了{}", result);
        }, executorService);

        log.info("stop.........");

    }

}
