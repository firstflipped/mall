package com.laughingather.gulimall.seckill.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 定时任务配置类
 * 1、spring定时任务中的cron表达式是由6位组成
 * 2、定时任务默认是阻塞的，但是在我们的应用场景中定时任务是不应该阻塞的
 * （1）、可以让业务以异步的方式自己提交到线程池，异步执行业务
 * CompletableFuture.runAsync(() -> {
 * <p>
 * });
 * （2）、spring支持定时任务线程池，覆盖 TaskSchedulingProperties 默认配置，但是由于版本bug原因在特定版本中不生效
 * spring.task.scheduling.pool.size = 10
 * （3）、定时任务以异步方式执行
 *
 * @EnableAsync注解开启异步功能
 * @Async注解标注到希望开启异步功能的方法上即可实现
 * @author：laughingather
 * @create：2021-11-12 2021/11/12
 */
@Configuration
@EnableAsync
@EnableScheduling
public class ScheduledConfig {

}

