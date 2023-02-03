package com.laughingather.gulimall.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.admin.entity.DictDetailEntity;
import com.laughingather.gulimall.admin.mapper.DictDetailMapper;
import com.laughingather.gulimall.admin.service.DictDetailService;
import org.springframework.stereotype.Service;

/**
 * 字典逻辑借口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2023-02-03 21:26:51
 */
@Service
public class DictDetailServiceImpl extends ServiceImpl<DictDetailMapper, DictDetailEntity> implements DictDetailService {
}
