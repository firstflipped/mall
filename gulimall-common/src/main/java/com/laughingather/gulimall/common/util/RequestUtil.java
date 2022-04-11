package com.laughingather.gulimall.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取客户端ip工具类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Slf4j
public class RequestUtil {

    private static final String UNKNOWN = "unknown";
    private static final String LOCALHOST_IP4 = "127.0.0.1";
    private static final String LOCALHOST_IP6 = "0:0:0:0:0:0:0:1";

    public static String getClientIp(HttpServletRequest request) {
        // X-Forwarded-For：Squid 服务代理
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            // Proxy-Client-IP：apache 服务代理
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            // WL-Proxy-Client-IP：WebLogic 服务代理
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (LOCALHOST_IP4.equals(ip) || LOCALHOST_IP6.equals(ip)) {
                try {
                    ip = InetAddress.getLocalHost().getHostAddress();
                } catch (UnknownHostException unknownhostexception) {
                    log.error("获取IP地址异常", unknownhostexception);
                }
            }
        } else {
            if (StringUtils.isNotBlank(ip)) {
                String[] split = ip.split(",");
                ip = split[0];
            }
        }
        return ip;
    }
}
