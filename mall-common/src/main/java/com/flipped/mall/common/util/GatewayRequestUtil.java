package com.flipped.mall.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

/**
 * 网关层获取客户端ip工具类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2023-02-27 13:57:49
 */
@Slf4j
public class GatewayRequestUtil {

    private static final String UNKNOWN = "unknown";
    private static final String LOCALHOST_IP4 = "127.0.0.1";
    private static final String LOCALHOST_IP6 = "0:0:0:0:0:0:0:1";

    public static String getClientIp(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();

        // X-Forwarded-For：Squid 服务代理
        String ip = headers.getFirst("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            // Proxy-Client-IP：apache 服务代理
            ip = headers.getFirst("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            // WL-Proxy-Client-IP：WebLogic 服务代理
            ip = headers.getFirst("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = headers.getFirst("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = headers.getFirst("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = Objects.requireNonNull(request.getRemoteAddress()).getAddress().getHostAddress();
            if (LOCALHOST_IP4.equals(ip) || LOCALHOST_IP6.equals(ip)) {
                try {
                    ip = InetAddress.getLocalHost().getHostAddress();
                } catch (UnknownHostException unknownhostexception) {
                    log.error("获取IP地址异常", unknownhostexception);
                }
            }
        }
        if (StringUtils.isNotBlank(ip) && ip.contains(",")) {
            ip = ip.substring(0, ip.indexOf(",")).trim();
        }
        return ip;
    }

}
