package com.laughingather.gulimall.auth;

import com.laughingather.gulimall.common.aspect.PlatformLogAspect;
import com.laughingather.gulimall.common.exception.ExceptionControllerAdvice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

/**
 * 认证服务启动类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@Import({PlatformLogAspect.class, ExceptionControllerAdvice.class})
public class GulimallAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallAuthApplication.class, args);
    }

}
