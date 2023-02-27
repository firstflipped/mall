package com.flipped.mall.gateway.route.service;

import com.flipped.mall.gateway.route.entity.PlatformLogEntity;
import reactor.core.publisher.Mono;

/**
 * 平台日志接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2023-02-27 09:36:48
 */
public interface PlatformLogService {

    /**
     * 保存平台日志
     *
     * @param platformLog 平台日志
     */
    Mono<PlatformLogEntity> savePlatformLog(PlatformLogEntity platformLog);
}
