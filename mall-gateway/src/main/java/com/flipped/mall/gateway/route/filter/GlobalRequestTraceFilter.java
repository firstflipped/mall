package com.flipped.mall.gateway.route.filter;

import cn.hutool.core.lang.UUID;
import com.flipped.mall.common.constant.GatewayConstant;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局请求日志拦截器
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2023-02-27 11:26:19
 */
@Slf4j
@Component
@ConditionalOnProperty(value = "mall.gateway.route.enhance.enable", havingValue = "true")
public class GlobalRequestTraceFilter implements GlobalFilter, Ordered {

    @Value("${mall.gateway.route.enhance.trace.enable:false}")
    private boolean traceEnhance;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 是否开启traceId追踪
        if (traceEnhance) {
            // ID生成
            String traceId = UUID.fastUUID().toString();
            MDC.put(GatewayConstant.LOG_TRACE_ID, traceId);
            ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate()
                    .headers(h -> h.add(GatewayConstant.MATE_TRACE_ID, traceId))
                    .build();
            ServerWebExchange build = exchange.mutate().request(serverHttpRequest).build();
            return chain.filter(build);
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}