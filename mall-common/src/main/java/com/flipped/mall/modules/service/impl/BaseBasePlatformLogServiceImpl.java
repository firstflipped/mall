package com.flipped.mall.modules.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flipped.mall.common.entity.PlatformLogEntity;
import com.flipped.mall.modules.mapper.BasePlatformLogMapper;
import com.flipped.mall.modules.service.BasePlatformLogService;
import org.springframework.stereotype.Service;

/**
 * 平台日志记录实现
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2023-07-25 22:30:19
 */
@Service
public class BaseBasePlatformLogServiceImpl extends ServiceImpl<BasePlatformLogMapper, PlatformLogEntity> implements BasePlatformLogService {

}
