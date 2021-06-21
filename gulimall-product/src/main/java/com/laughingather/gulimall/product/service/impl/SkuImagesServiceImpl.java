package com.laughingather.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.laughingather.gulimall.product.dao.SkuImagesDao;
import com.laughingather.gulimall.product.entity.SkuImagesEntity;
import com.laughingather.gulimall.product.service.SkuImagesService;

import javax.annotation.Resource;
import java.util.List;


@Service("skuImagesService")
public class SkuImagesServiceImpl extends ServiceImpl<SkuImagesDao, SkuImagesEntity> implements SkuImagesService {

    @Resource
    private SkuImagesDao skuImagesDao;

    @Override
    public List<SkuImagesEntity> listImagesBySkuId(Long skuId) {
        QueryWrapper<SkuImagesEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SkuImagesEntity::getSkuId, skuId);
        return list(queryWrapper);
    }
}