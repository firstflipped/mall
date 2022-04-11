package com.laughingather.gulimall.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * restTemplate
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Configuration
public class MyRestConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
