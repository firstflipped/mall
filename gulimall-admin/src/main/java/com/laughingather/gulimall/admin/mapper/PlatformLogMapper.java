package com.laughingather.gulimall.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laughingather.gulimall.common.entity.PlatformLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 平台日志持久层
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Mapper
public interface PlatformLogMapper extends BaseMapper<PlatformLog> {
}

