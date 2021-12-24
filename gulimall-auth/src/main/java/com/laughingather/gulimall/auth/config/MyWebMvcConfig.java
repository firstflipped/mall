package com.laughingather.gulimall.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 视图映射自定义配置
 *
 * @author：laughingather
 */
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    /**
     * 视图映射
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        /**
         * @GetMapping({"/", "/login.html"})    第一个参数
         * public String loginPage() {
         *     return "login";                  第二个参数
         * }
         */
        registry.addViewController("/register.html").setViewName("register");
    }
}
