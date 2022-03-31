package com.laughingather.gulimall.admin.config;

import com.laughingather.gulimall.common.config.BaseMybatisPlusConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis-plus配置
 *
 * @author：laughingather
 * @create：2022-01-11
 * @EnableTransactionManagement:开启事务
 */
@Configuration
@MapperScan(basePackages = {"com.laughingather.gulimall.admin.mapper"})
public class MybatisPlusConfig extends BaseMybatisPlusConfig {
}