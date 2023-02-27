package com.flipped.mall.gateway.route.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * 平台日志
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2023-02-27 10:14:33
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlatformLogEntity {

    /**
     * id
     */
    @Id
    private String id;

    /**
     * 请求地点
     */
    private String clientLocation;

    /**
     * 请求ip
     */
    private String clientIp;

    /**
     * 请求uri
     */
    private String requestUri;

    /**
     * 请求方法
     */
    private String requestMethod;

    /**
     * 目标uri
     */
    private String targetUri;

    /**
     * 目标服务
     */
    private String targetServer;

    /**
     * 请求时间
     */
    private String createTime;


}
