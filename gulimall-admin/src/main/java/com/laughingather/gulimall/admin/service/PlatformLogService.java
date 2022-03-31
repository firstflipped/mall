package com.laughingather.gulimall.admin.service;

import com.laughingather.gulimall.common.entity.PlatformLog;

/**
 * 平台日志逻辑接口
 *
 * @author：laughingather
 * @create：2021-12-09 2021/12/9
 */
public interface PlatformLogService {

    /**
     * 保存日志
     *
     * @param platformLog
     */
    void saveLog(PlatformLog platformLog);

}

