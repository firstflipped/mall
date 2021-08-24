package com.laughingather.gulimall.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 购物车模块启动类
 *
 * @author：laughingather
 * @create：2021-07-28 2021/7/28
 */
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class GulimallCartApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallCartApplication.class, args);
    }

}

