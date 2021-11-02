package com.laughingather.gulimall.product.config;

import com.laughingather.gulimall.common.config.BaseMybatisPlusConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mybatis-plus配置
 * @EnableTransactionManagement:开启事务
 *
 * @author Mark laughingather@gmail.com
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.laughingather.gulimall.product.dao")
public class MybatisPlusConfig extends BaseMybatisPlusConfig {
}