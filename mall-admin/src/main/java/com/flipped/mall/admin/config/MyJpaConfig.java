package com.flipped.mall.admin.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * jpa配置
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@EnableJpaRepositories
@EnableTransactionManagement
public class MyJpaConfig {

}