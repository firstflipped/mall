package com.laughingather.gulimall.admin.config;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 唯一id 雪花算法
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Configuration
public class MySnowflakeConfig {

    @Bean
    public Snowflake snowflake() {
        return IdUtil.createSnowflake(1, 1);
    }

}

