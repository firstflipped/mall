package com.laughingather.gulimall.generator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * @author WangJie
 */
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@MapperScan("com.laughingather.gulimall.generator.dao")
public class GulimallGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(GulimallGeneratorApplication.class, args);
	}
}
