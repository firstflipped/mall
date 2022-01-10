package com.laughingather.gulimall.gateway.filter;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.stream.Stream;

/**
 * 安全拦截全局过滤器
 *
 * @author：laughingather
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class GlobalAuthFilter implements GlobalFilter, Ordered {

    @Value("${auth.ignore.urls}")
    private String authIgnoreUrls;

    @Override
    @SneakyThrows
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        // 路由白名单，直接放行
        String uri = request.getURI().getPath();
        boolean flag = Stream.of(authIgnoreUrls.split(","))
                .anyMatch(ignoreUrl -> uri.startsWith(ignoreUrl.trim()));
        if (flag) {
            return chain.filter(exchange);
        }

        // 获取token
        String authorization = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);


        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
