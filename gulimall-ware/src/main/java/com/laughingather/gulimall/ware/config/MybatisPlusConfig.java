package com.laughingather.gulimall.ware.config;

import com.laughingather.gulimall.common.config.BaseMybatisPlusConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mybatis-plus配置
 *
 * @author Mark sunlightcs@gmail.com
 */
@Configuration
@EnableTransactionManagement    // 开启事务
@MapperScan(basePackages = "com.laughingather.gulimall.ware.dao")
public class MybatisPlusConfig extends BaseMybatisPlusConfig {
}