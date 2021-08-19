package com.laughingather.gulimall.cart.config;

import com.laughingather.gulimall.cart.interceptor.CartInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author WangJie
 */
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {


    /**
     * 添加过滤器到容器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CartInterceptor());
    }

}
