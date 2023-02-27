package com.flipped.mall.gateway.route.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.server.ServerWebExchange;

/**
 * 路由拓展增强接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2023-02-27 09:43:10
 */
public interface RouteEnhanceService {

    /**
     * 保存请求日志
     *
     * @param exchange
     */
    @Async
    void saveRequestLog(ServerWebExchange exchange);

}
