package com.laughingather.gulimall.member.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author WangJie
 */
@Configuration
public class MyRestConfig {

    /**
     * 使用restTemplate调用外部接口时，切记不要加 @LoadBalanced 注解
     *
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
