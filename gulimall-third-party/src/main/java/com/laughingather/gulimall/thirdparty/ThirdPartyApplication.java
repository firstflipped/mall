package com.laughingather.gulimall.thirdparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 第三方服务启动类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ThirdPartyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThirdPartyApplication.class, args);
    }

}
