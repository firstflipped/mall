package com.flipped.mall.reptile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 爬虫服务启动类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since ${YEAR}-${MONTH}-${DAY} ${HOUR}:${MINUTE}:${SECOND}
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MallSpiderApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallSpiderApplication.class, args);
    }
}