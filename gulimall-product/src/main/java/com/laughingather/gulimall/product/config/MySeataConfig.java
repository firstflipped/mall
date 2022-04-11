package com.laughingather.gulimall.product.config;

import org.springframework.context.annotation.Configuration;

/**
 * seata控制分布式事务
 * <p>
 * AT模式：该模式仅适用于并发较小的后台管理类场景，不适用于高并发场景
 *
 * <p>
 * <p>
 * 1、每一个想要使用分布式事务的微服务数据库都需要创建 undo_log 表;
 * CREATE TABLE `undo_log`
 * (
 * `id`            BIGINT(20)   NOT NULL AUTO_INCREMENT,
 * `branch_id`     BIGINT(20)   NOT NULL,
 * `xid`           VARCHAR(100) NOT NULL,
 * `context`       VARCHAR(128) NOT NULL,
 * `rollback_info` LONGBLOB     NOT NULL,
 * `log_status`    INT(11)      NOT NULL,
 * `log_created`   DATETIME     NOT NULL,
 * `log_modified`  DATETIME     NOT NULL,
 * `ext`           VARCHAR(100) DEFAULT NULL,
 * PRIMARY KEY (`id`),
 * UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
 * ) ENGINE = InnoDB
 * AUTO_INCREMENT = 1
 * DEFAULT CHARSET = utf8
 * <p>
 * 2、安装事务协调器 seata-server   主要版本需要与引入jar包中的seata版本保持一致
 * 解压并启动 seata-server
 * registry.conf: 注册中心配置：修改registry type=nacos
 * <p>
 * 3、整合
 * (1) 导入依赖包  spring-cloud-starter-alibaba-seata
 * (2) 手动配置数据源代理
 * 所有想要使用分布式事务的微服务都应该使用 seata DataSourceProxy代理自己的数据源
 * (3) 微服务resource中加入file.conf  registry.cong文件
 * file.conf文件  service.vgroupMapping: ${spring.application.name}-fescar-service-group
 * application.yml文件  spring.cloud.alibaba.seata.tx-service-group: ${spring.application.name}-fescar-service-group
 * <p>
 * 4、使用
 * 给需要进行全局事务控制的方法加上 @GlobalTransactional 注解
 *
 * @author laughingather
 */
@Configuration
public class MySeataConfig {

    /**
     * 从配置文件获取属性构造datasource，注意前缀，这里用的是druid，根据自己情况配置,
     * 原生datasource前缀取"spring.datasource"
     *
     * @return
     */
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DruidDataSource druidDataSource() {
//        return new DruidDataSource();
//    }

    /**
     * 构造datasource代理对象，替换原来的datasource
     *
     * @param druidDataSource
     * @return
     */
//    @Primary
//    @Bean("dataSource")
//    public DataSourceProxy dataSourceProxy(DataSource druidDataSource) {
//        return new DataSourceProxy(druidDataSource);
//    }

}