package com.flipped.mall.coupon.config;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SpringDoc配置
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Configuration
public class MySpringDocConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("GuliMall API").description("SpringDoc API").version("v1.0.0").license(new License().name("Apache 2.0").url("https://www.laughingather.com")))
                .externalDocs(new ExternalDocumentation().description("一个用于自学的商城项目").url("https://www.laughingather.com"));
    }
}
