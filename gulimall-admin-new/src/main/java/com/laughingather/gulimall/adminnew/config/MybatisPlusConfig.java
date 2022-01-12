package com.laughingather.gulimall.adminnew.config;

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
@MapperScan(basePackages = {"com.laughingather.gulimall.adminnew.mapper", "com.laughingather.gulimall.common.mapper"})
public class MybatisPlusConfig extends BaseMybatisPlusConfig {
}