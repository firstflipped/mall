package com.flipped.mall.admin.config;

import com.flipped.mall.common.config.BaseMybatisPlusConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis-plus配置
 *
 * <p>
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @MapperScan(basePackages = {"com.flipped.mall.admin.mapper", "com.flipped.mall.modules.mapper"})
 * 扫描mapper接口
 * 1. com.flipped.mall.admin.mapper
 * 2. com.flipped.mall.modules.mapper
 * <p>
 * 还需要配置 .xml 扫描路径
 *
 * </p>
 * @since 2022-04-11 19:35:16
 */
@Configuration
@MapperScan(basePackages = {"com.flipped.mall.admin.mapper", "com.flipped.mall.modules.mapper"})
public class MybatisPlusConfig extends BaseMybatisPlusConfig {
}