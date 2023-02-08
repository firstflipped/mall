package com.flipped.mall.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flipped.mall.admin.entity.DictDetailEntity;
import com.flipped.mall.admin.mapper.DictDetailMapper;
import com.flipped.mall.admin.service.DictDetailService;
import com.flipped.mall.admin.util.SecurityUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 字典逻辑借口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2023-02-03 21:26:51
 */
@Service
public class DictDetailServiceImpl extends ServiceImpl<DictDetailMapper, DictDetailEntity> implements DictDetailService {

    @Resource
    private DictDetailMapper dictDetailMapper;

    @Override
    public void saveDictDetail(DictDetailEntity dictDetailEntity) {
        dictDetailEntity.setCreateBy(SecurityUtil.getUsername());

        dictDetailMapper.insert(dictDetailEntity);
    }
}
