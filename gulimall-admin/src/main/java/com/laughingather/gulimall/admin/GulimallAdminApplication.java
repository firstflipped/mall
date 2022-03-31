package com.laughingather.gulimall.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 启动类
 *
 * @SpringBootApplication(scanBasePackages = {"com.laughingather.gulimall.*"})   注入common包中的类
 * @author：laughingather
 * @create：2021-11-24 2021/11/24
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GulimallAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallAdminApplication.class, args);
    }

}

