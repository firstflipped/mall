package com.laughingather.gulimall.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 异步编排线程池参数
 *
 * @author：laughingather
 * @create：2021-10-09 2021/10/9
 */
@Data
@Component
@ConfigurationProperties(prefix = "gulimall.thread")
public class ThreadPoolConfigProperties {

    /**
     * 核心线程数
     */
    private Integer coreSize;

    /**
     * 最大线程数
     */
    private Integer maximumPoolSize;

    /**
     * 空闲线程关闭时间
     */
    private Integer keepAliveTime;

}

