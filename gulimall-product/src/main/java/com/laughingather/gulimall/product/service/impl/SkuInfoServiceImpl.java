package com.laughingather.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.product.dao.SkuInfoDao;
import com.laughingather.gulimall.product.entity.SkuInfoEntity;
import com.laughingather.gulimall.product.entity.query.SkuInfoQuery;
import com.laughingather.gulimall.product.service.SkuInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {

    @Resource
    private SkuInfoDao skuInfoDao;

    @Override
    public MyPage<SkuInfoEntity> pageSkuInfoByParams(SkuInfoQuery skuInfoQuery) {
        IPage<SkuInfoEntity> page = new Page<>(skuInfoQuery.getPageNumber(), skuInfoQuery.getPageSize());
        IPage<SkuInfoEntity> skuInfoIPage = skuInfoDao.pageSkuInfoByParams(page, skuInfoQuery);
        return MyPage.restPage(skuInfoIPage);
    }
}