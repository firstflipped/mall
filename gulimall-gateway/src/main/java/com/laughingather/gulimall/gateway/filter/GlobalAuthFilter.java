package com.laughingather.gulimall.gateway.filter;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.common.constant.AuthConstants;
import com.laughingather.gulimall.common.entity.JwtPayLoad;
import com.laughingather.gulimall.common.util.JsonUtil;
import com.laughingather.gulimall.common.util.TokenProvider;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * 权限拦截全局过滤器
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
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
            return out(response, "请求头 Authorization 为空或格式不正确");
        }

        // 如果token校验失败则返回
        String token = authorization.replace(AuthConstants.TOKEN_PREFIX, "");
        if (!TokenProvider.checkToken(token)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return out(response, "登录凭证不合法");
        }

        JwtPayLoad jwtPayLoad = TokenProvider.getJwtPayLoad(token);
        if (Objects.isNull(jwtPayLoad)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return out(response, "登录凭证载荷为空");
        }

        // 将用户名和用户id放到请求头，进入其他微服务
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

    private Mono<Void> out(ServerHttpResponse response, String message) {
        MyResult<Void> result = new MyResult<>();
        result.setCode(401);
        result.setData(null);
        result.setMessage(message);

        byte[] bytes = JsonUtil.obj2String(result).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        // 指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        // 输出http响应
        return response.writeWith(Mono.just(buffer));
    }
}
