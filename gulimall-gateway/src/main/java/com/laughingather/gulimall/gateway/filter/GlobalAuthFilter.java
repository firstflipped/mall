package com.laughingather.gulimall.gateway.filter;

import com.laughingather.gulimall.common.constant.AuthConstants;
import com.laughingather.gulimall.common.entity.JwtPayLoad;
import com.laughingather.gulimall.common.util.TokenProvider;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * 权限拦截全局过滤器
 *
 * @author：laughingather
 */
@Slf4j
@Component
public class GlobalAuthFilter implements GlobalFilter, Ordered {

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Value("${auth.ignore.urls}")
    private String authIgnoreUrls;

    @Override
    @SneakyThrows
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        // OPTIONS预检请求直接放行
        if (Objects.equals(request.getMethod(), HttpMethod.OPTIONS)) {
            return chain.filter(exchange);
        }

        // 路由白名单，直接放行
        String uri = request.getURI().getPath();
        boolean flag = Stream.of(authIgnoreUrls.split(","))
                .anyMatch(ignoreUrl -> antPathMatcher.match(ignoreUrl, uri));
        if (flag) {
            return chain.filter(exchange);
        }

        // 获取token
        String authorization = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        // 如果token为空则返回Unauthorized
        if (StringUtils.isBlank(authorization) || !authorization.startsWith(AuthConstants.TOKEN_PREFIX)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        // 如果token校验失败则返回
        String token = authorization.replace(AuthConstants.TOKEN_PREFIX, "");
        if (!TokenProvider.checkToken(token)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        JwtPayLoad jwtPayLoad = TokenProvider.getJwtPayLoad(token);
        if (Objects.isNull(jwtPayLoad)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        ServerHttpRequest buildRequest = request.mutate()
                .header(AuthConstants.USERID, jwtPayLoad.getUserid().toString())
                .header(AuthConstants.USERNAME, jwtPayLoad.getUsername())
                .build();
        exchange = exchange.mutate().request(buildRequest).build();
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
