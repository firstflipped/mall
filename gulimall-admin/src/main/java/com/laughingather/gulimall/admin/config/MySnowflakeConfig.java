package com.laughingather.gulimall.admin.config;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 唯一id 雪花算法
 *
 * @author：laughingather
 * @create：2021-05-26
 */
@Configuration
public class MySnowflakeConfig {

    @Bean
    public Snowflake snowflake() {
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        return snowflake;
    }

}

