package com.flipped.mall.product.config;

import com.flipped.mall.common.config.BaseMybatisPlusConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mybatis-plus配置
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.flipped.mall.product.dao")
public class MybatisPlusConfig extends BaseMybatisPlusConfig {
}