package com.flipped.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flipped.mall.product.dao.SkuImagesDao;
import com.flipped.mall.product.entity.SkuImagesEntity;
import com.flipped.mall.product.service.SkuImagesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * sku图片管理逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
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