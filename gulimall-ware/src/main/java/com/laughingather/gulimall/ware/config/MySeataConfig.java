package com.laughingather.gulimall.ware.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * seata控制分布式事务
 * 1、每一个想要使用分布式事务的微服务数据库都需要创建 undo_log 表;
 * 2、安装事务协调器 seata-server   主要版本需要与引入jar包中的seata版本保持一致
 * 3、整合
 * (1) 导入依赖包  spring-cloud-starter-alibaba-seata
 * (2) 解压并启动 seata-server
 * registry.conf: 注册中心配置：修改registry type=nacos
 * (3) 所有想要使用分布式事务的微服务都应该使用 seata DataSourceProxy代理自己的数据源
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

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource druidDataSource() {
        return new DruidDataSource();
    }

    /**
     * 构造datasource代理对象，替换原来的datasource
     *
     * @param druidDataSource
     * @return
     */
    @Primary
    @Bean("dataSource")
    public DataSourceProxy dataSourceProxy(DataSource druidDataSource) {
        return new DataSourceProxy(druidDataSource);
    }


//    @Bean
//    public SqlSessionFactory sqlSessionFactoryBean(DataSourceProxy dataSourceProxy) throws Exception {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSourceProxy);
//        return sqlSessionFactoryBean.getObject();
//    }


}