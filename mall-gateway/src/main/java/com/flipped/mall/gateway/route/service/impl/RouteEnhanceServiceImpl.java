package com.flipped.mall.gateway.route.service.impl;

import com.flipped.mall.common.util.GatewayRequestUtil;
import com.flipped.mall.gateway.route.entity.PlatformLogEntity;
import com.flipped.mall.gateway.route.service.PlatformLogService;
import com.flipped.mall.gateway.route.service.RouteEnhanceService;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;

import javax.annotation.Resource;
import java.net.URI;
import java.util.LinkedHashSet;

/**
 * 路由拓展增强实现
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2023-02-27 09:43:42
 */
@Service
public class RouteEnhanceServiceImpl implements RouteEnhanceService {

    @Resource
    private PlatformLogService platformLogService;

    @Override
    public void saveRequestLog(ServerWebExchange exchange) {
        URI originalUri = getGatewayOriginalRequseyUri(exchange);
        URI uri = getGatewayRequestUri(exchange);
        Route route = getGatewayRoute(exchange);

        ServerHttpRequest request = exchange.getRequest();
        String clientIp = GatewayRequestUtil.getClientIp(request);


        PlatformLogEntity platformLog = PlatformLogEntity.builder()
                .clientLocation(null)
                .clientIp(clientIp)
                .requestUri(originalUri != null ? originalUri.getPath() : null)
                .requestMethod(request.getMethodValue())
                .targetUri(uri != null ? uri.getPath() : null)
                .targetServer(route != null ? route.getId() : null)
                .build();

        platformLogService.savePlatformLog(platformLog).subscribe();
    }


    private URI getGatewayOriginalRequseyUri(ServerWebExchange exchange) {
        LinkedHashSet<URI> uris = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        URI originUri = null;
        if (uris != null) {
            originUri = uris.stream().findFirst().orElse(null);
        }
        return originUri;
    }

    private URI getGatewayRequestUri(ServerWebExchange exchange) {
        return exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
    }

    private Route getGatewayRoute(ServerWebExchange exchange) {
        return exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
    }

}
