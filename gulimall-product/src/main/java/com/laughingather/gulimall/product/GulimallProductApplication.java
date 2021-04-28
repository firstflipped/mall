package com.laughingather.gulimall.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author WangJie
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.laughingather.gulimall.product.feign.service")
public class GulimallProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(GulimallProductApplication.class, args);
	}

}
