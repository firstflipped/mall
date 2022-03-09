package com.laughingather.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.laughingather.gulimall.product.dao.SkuImagesDao;
import com.laughingather.gulimall.product.entity.SkuImagesEntity;
import com.laughingather.gulimall.product.service.SkuImagesService;

import javax.annotation.Resource;
import java.util.List;


/**
 * sku图片管理逻辑接口
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
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