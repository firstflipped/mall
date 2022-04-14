package com.laughingather.gulimall.admin.service.impl;

import com.laughingather.gulimall.admin.entity.PlatformLog;
import com.laughingather.gulimall.admin.mapper.PlatformLogMapper;
import com.laughingather.gulimall.admin.service.PlatformLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 平台日志逻辑实现
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Service
public class PlatformLogServiceImpl implements PlatformLogService {

    @Resource
    private PlatformLogMapper platformLogMapper;

    @Override
    public void saveLog(PlatformLog platformLog) {
        platformLogMapper.insert(platformLog);
    }

}

