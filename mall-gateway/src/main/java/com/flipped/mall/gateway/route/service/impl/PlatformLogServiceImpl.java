package com.flipped.mall.gateway.route.service.impl;

import com.flipped.mall.gateway.route.entity.PlatformLogEntity;
import com.flipped.mall.gateway.route.service.PlatformLogService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 平台日志实现类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2023-02-27 09:37:34
 */
@Service
public class PlatformLogServiceImpl implements PlatformLogService {
    
    @Override
    public Mono<PlatformLogEntity> savePlatformLog(PlatformLogEntity platformLog) {
        platformLog.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        return Mono.empty();
    }
}
