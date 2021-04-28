package com.laughingather.gulimall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.ware.dao.WareInfoDao;
import com.laughingather.gulimall.ware.entity.WareInfoEntity;
import com.laughingather.gulimall.ware.entity.query.WareInfoQuery;
import com.laughingather.gulimall.ware.service.WareInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service("wareInfoService")
public class WareInfoServiceImpl extends ServiceImpl<WareInfoDao, WareInfoEntity> implements WareInfoService {

    @Resource
    private WareInfoDao wareInfoDao;

    @Override
    public MyPage<WareInfoEntity> pageWareInfoByParams(WareInfoQuery wareInfoQuery) {
        IPage<WareInfoEntity> page = new Page<>(wareInfoQuery.getPageNumber(), wareInfoQuery.getPageSize());
        QueryWrapper<WareInfoEntity> queryWrapper = null;
        if (StringUtils.isNotBlank(wareInfoQuery.getKey())) {
            queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(WareInfoEntity::getId, wareInfoQuery.getKey())
                    .or().like(WareInfoEntity::getName, wareInfoQuery.getKey())
                    .or().like(WareInfoEntity::getAddress, wareInfoQuery.getKey());
        }
        IPage<WareInfoEntity> wareInfoIPage = wareInfoDao.selectPage(page, queryWrapper);
        return MyPage.restPage(wareInfoIPage);
    }
}