package com.laughingather.gulimall.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * restTemplate
 *
 * @author：laughingather
 * @create：2021-06-22 0:13
 */
@Configuration
public class MyRestConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
