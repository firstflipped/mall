package com.laughingather.gulimall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.ware.dao.WareSkuDao;
import com.laughingather.gulimall.ware.entity.WareSkuEntity;
import com.laughingather.gulimall.ware.entity.query.WareSkuQuery;
import com.laughingather.gulimall.ware.service.WareSkuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {

    @Resource
    private WareSkuDao wareSkuDao;

    @Override
    public MyPage<WareSkuEntity> pageWareSkuByParams(WareSkuQuery wareSkuQuery) {
        IPage<WareSkuEntity> page = new Page<>(wareSkuQuery.getPageNumber(), wareSkuQuery.getPageSize());
        QueryWrapper<WareSkuEntity> queryWrapper = new QueryWrapper<>();
        if (wareSkuQuery.getWareId() != null) {
            queryWrapper.lambda().eq(WareSkuEntity::getWareId, wareSkuQuery.getWareId());
        }
        if (wareSkuQuery.getSkuId() != null) {
            queryWrapper.lambda().eq(WareSkuEntity::getSkuId, wareSkuQuery.getSkuId());
        }

        IPage<WareSkuEntity> wareSkuIPage = wareSkuDao.selectPage(page, queryWrapper);
        return MyPage.restPage(wareSkuIPage);
    }
}