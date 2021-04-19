package com.laughingather.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.product.dao.BrandDao;
import com.laughingather.gulimall.product.entity.BrandEntity;
import com.laughingather.gulimall.product.entity.query.BrandQuery;
import com.laughingather.gulimall.product.service.BrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Resource
    private BrandDao brandDao;

    @Override
    public IPage<BrandEntity> pageBrandsByParams(BrandQuery brandQuery) {
        IPage<BrandEntity> page = new Page<>(brandQuery.getPageNumber(), brandQuery.getPageSize());
        QueryWrapper<BrandEntity> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(brandQuery.getName())) {
            queryWrapper.lambda().like(BrandEntity::getName, brandQuery.getName());
        }

        return brandDao.selectPage(page, queryWrapper);
    }
}