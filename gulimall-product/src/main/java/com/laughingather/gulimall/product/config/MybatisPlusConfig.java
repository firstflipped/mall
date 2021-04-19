package com.laughingather.gulimall.product.config;

import com.laughingather.gulimall.common.config.BaseMybatisPlusConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis-plus配置
 *
 * @author Mark sunlightcs@gmail.com
 */
@Configuration
@MapperScan(basePackages = "com.laughingather.gulimall.product.dao")
public class MybatisPlusConfig extends BaseMybatisPlusConfig {
}