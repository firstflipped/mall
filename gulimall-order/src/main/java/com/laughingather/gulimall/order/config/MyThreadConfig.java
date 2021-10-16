package com.laughingather.gulimall.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 异步编排配置
 *
 * @author：laughingather
 * @create：2021-10-09 2021/10/9
 */
@Configuration
public class MyThreadConfig {

    @Bean
    public ThreadPoolExecutor threadPoolExecutor(ThreadPoolConfigProperties threadPoolConfigProperties) {
        /**
         * corePoolSize: 核心线程池数量
         * maximumPoolSize: 最大线程池数量
         * keepAliveTime: 空闲线程关闭时间
         * unit: 时间单位
         * workQueue: 阻塞队列长度
         * threadFactory: 线程工厂  Executors.defaultThreadFactory()表示默认工厂
         * handler: 拒绝策略
         */
        return new ThreadPoolExecutor(threadPoolConfigProperties.getCoreSize(),
                threadPoolConfigProperties.getMaximumPoolSize(),
                threadPoolConfigProperties.getKeepAliveTime(),
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(100000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
    }

}

