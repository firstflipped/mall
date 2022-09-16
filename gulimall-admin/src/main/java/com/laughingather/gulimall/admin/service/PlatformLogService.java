package com.laughingather.gulimall.admin.service;


import com.laughingather.gulimall.admin.entity.query.PlatformLogQuery;
import com.laughingather.gulimall.admin.entity.vo.PlatformLogExcelVO;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.entity.PlatformLog;

import java.util.List;

/**
 * 平台日志逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface PlatformLogService {

    /**
     * 分页查询日志列表
     *
     * @param platformLogQuery 日志列表查询条件
     * @return 日志列表
     */
    MyPage<PlatformLog> listPlatformLogsWithPage(PlatformLogQuery platformLogQuery);

    /**
     * 查询导出日志列表
     *
     * @param platformLogQuery 日志列表查询条件
     * @return 日志列表
     */
    List<PlatformLogExcelVO> listPlatformLogsWithExport(PlatformLogQuery platformLogQuery);
}

