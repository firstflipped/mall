package com.laughingather.gulimall.seckill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 秒杀服务启动类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class GulimallSecKillApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallSecKillApplication.class, args);
    }

}

