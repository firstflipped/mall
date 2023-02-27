package com.flipped.mall.gateway.route.filter;

import com.flipped.mall.gateway.route.service.RouteEnhanceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * 全局请求拦截器
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2023-02-27 11:26:19
 */
@Component
@Order(0)
public class GlobalRequestFilter implements GlobalFilter {

    @Resource
    private RouteEnhanceService routeEnhanceService;

    @Value("${mall.gateway.route.enhance:False}")
    private Boolean enhance;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (enhance) {
            routeEnhanceService.saveRequestLog(exchange);
        }
        return chain.filter(exchange);
    }
}
