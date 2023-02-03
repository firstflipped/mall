package com.laughingather.gulimall.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.admin.entity.DictEntity;
import com.laughingather.gulimall.admin.entity.query.DictQuery;
import com.laughingather.gulimall.admin.mapper.DictMapper;
import com.laughingather.gulimall.admin.service.DictService;
import com.laughingather.gulimall.common.entity.api.MyPage;
import org.apache.commons.lang3.StringUtils;
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
public class DictServiceImpl extends ServiceImpl<DictMapper, DictEntity> implements DictService {

    @Resource
    private DictMapper dictMapper;

    @Override
    public MyPage<DictEntity> listDictWithPage(DictQuery dictQuery) {
        // 分页信息
        IPage<DictEntity> page = Page.of(dictQuery.getPn(), dictQuery.getPs());
        // 查询条件
        LambdaQueryWrapper<DictEntity> queryWrapper = null;
        if (StringUtils.isNotBlank(dictQuery.getDictName())) {
            queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.like(DictEntity::getDictName, dictQuery.getDictName());
        }

        IPage<DictEntity> dictPage = dictMapper.selectPage(page, queryWrapper);
        return MyPage.restPage(dictPage);
    }
}
