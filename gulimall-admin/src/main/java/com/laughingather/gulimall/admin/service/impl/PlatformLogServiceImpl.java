package com.laughingather.gulimall.admin.service.impl;

import com.laughingather.gulimall.admin.mapper.PlatformLogMapper;
import com.laughingather.gulimall.admin.service.PlatformLogService;
import com.laughingather.gulimall.common.entity.PlatformLog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 平台日志逻辑实现
 *
 * @author：laughingather
 * @create：2021-12-09 2021/12/9
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

