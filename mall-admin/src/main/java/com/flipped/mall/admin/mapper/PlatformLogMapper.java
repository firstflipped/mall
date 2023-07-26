package com.flipped.mall.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flipped.mall.admin.entity.query.PlatformLogQuery;
import com.flipped.mall.admin.entity.vo.PlatformLogExcelVO;
import com.flipped.mall.common.entity.PlatformLogEntity;

import java.util.List;

/**
 * 平台日志持久层
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface PlatformLogMapper extends BaseMapper<PlatformLogEntity> {

    /**
     * 查询日志列表（导出接口使用）
     *
     * @param platformLogQuery 日志列表查询条件
     * @return 日志列表导出类
     */
    List<PlatformLogExcelVO> listPlatformLogsWithExport(PlatformLogQuery platformLogQuery);

}

