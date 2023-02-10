package com.flipped.mall.gateway.config;

import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

import static cn.hutool.core.lang.Opt.ofNullable;

/**
 * 全局SpringDoc配置
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2023-02-10 16:39:34
 */
@Configuration
@ConditionalOnProperty(name = "springdoc.api-docs.enabled", havingValue = "true")
public class GlobalSpringDocConfig {

    private static final String DISCOVERY_CLIENT_ID_PRE = "ReactiveCompositeDiscoveryClient_";

    @Value("${spring.application.name}")
    private String selfServiceName;

    /**
     * 动态分组聚合springdoc文档
     * <p>
     * 相当于在配置文件中进行此配置
     * springdoc:
     * swagger-ui:
     * urls:
     * - name: mall-admin
     * url: /v3/api-docs/mall-admin
     * - name: mall-auth
     * url: /v3/api-docs/mall-auth
     * </p>
     *
     * @param swaggerUiConfigParameters swagger配置参数
     * @param locator                   路由
     * @return 分组springdoc文档
     */
    @Bean
    @Lazy(false)
    public List<GroupedOpenApi> groupedOpenApis(SwaggerUiConfigParameters swaggerUiConfigParameters, RouteDefinitionLocator locator) {
        List<GroupedOpenApi> groups = new ArrayList<>();
        List<RouteDefinition> definitions = ofNullable(locator.getRouteDefinitions().collectList().block())
                .orElseGet(ArrayList::new);
        final String selfServiceId = DISCOVERY_CLIENT_ID_PRE + selfServiceName;
        definitions.stream().filter(routeDefinition -> ofNullable(routeDefinition.getId())
                        // 只保留服务级别的路由。
                        .filter(id -> id.startsWith(DISCOVERY_CLIENT_ID_PRE))
                        // 排除本系统。
                        .filter(id -> !selfServiceId.equalsIgnoreCase(id)).isPresent())
                .forEach(definition -> {
                    String serviceId = definition.getId().replaceAll(DISCOVERY_CLIENT_ID_PRE, "");
                    swaggerUiConfigParameters.addGroup(serviceId);
                });


        return groups;
    }
}
