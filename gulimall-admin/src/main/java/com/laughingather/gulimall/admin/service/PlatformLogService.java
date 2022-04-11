package com.laughingather.gulimall.admin.service;

import com.laughingather.gulimall.common.entity.PlatformLog;

/**
 * 平台日志逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface PlatformLogService {

    /**
     * 保存日志
     *
     * @param platformLog
     */
    void saveLog(PlatformLog platformLog);

}

