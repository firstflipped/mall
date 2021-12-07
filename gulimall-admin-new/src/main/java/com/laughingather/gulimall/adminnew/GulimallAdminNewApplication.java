package com.laughingather.gulimall.adminnew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 启动类
 *
 * @author：laughingather
 * @create：2021-11-24 2021/11/24
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties
public class GulimallAdminNewApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallAdminNewApplication.class, args);
    }

}

