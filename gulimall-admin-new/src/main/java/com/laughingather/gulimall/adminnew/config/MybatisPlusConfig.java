package com.laughingather.gulimall.adminnew.config;

import com.laughingather.gulimall.common.config.BaseMybatisPlusConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis-plus配置
 *
 * @author Mark laughingather@gmail.com
 * @EnableTransactionManagement:开启事务
 */
@Configuration
@MapperScan(basePackages = "com.laughingather.gulimall.adminnew.mapper")
public class MybatisPlusConfig extends BaseMybatisPlusConfig {
}