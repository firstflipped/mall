package com.laughingather.gulimall.adminnew.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * jpa配置
 *
 * @author laughingather
 */
@EnableJpaRepositories
@EnableTransactionManagement
public class MyJpaConfig {

}