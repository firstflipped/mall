package com.laughingather.gulimall.seckill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 秒杀服务启动类
 *
 * @author：laughingather
 * @create：2021-11-12 2021/11/12
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class GulimallSeckillApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallSeckillApplication.class, args);
    }

}

