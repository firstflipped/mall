package com.flipped.mall.gateway.route.filter;

import com.flipped.mall.gateway.route.service.RouteEnhanceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * 全局请求日志拦截器
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2023-02-27 11:26:19
 */
@Order(0)
@Component
@ConditionalOnProperty(value = "mall.gateway.route.enhance.enable", havingValue = "true")
public class GlobalRequestLogFilter implements GlobalFilter {

    @Resource
    private RouteEnhanceService routeEnhanceService;

    @Value("${mall.gateway.route.enhance.log.enable:false}")
    private boolean logEnhance;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (logEnhance) {
            routeEnhanceService.saveRequestLog(exchange);
        }
        return chain.filter(exchange);
    }
}
