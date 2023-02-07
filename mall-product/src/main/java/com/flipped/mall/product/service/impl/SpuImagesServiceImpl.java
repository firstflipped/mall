package com.flipped.mall.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flipped.mall.product.dao.SpuImagesDao;
import com.flipped.mall.product.entity.SpuImagesEntity;
import com.flipped.mall.product.service.SpuImagesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * spu图片逻辑实现
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Service("spuImagesService")
public class SpuImagesServiceImpl extends ServiceImpl<SpuImagesDao, SpuImagesEntity> implements SpuImagesService {

    @Resource
    private SpuImagesDao spuImagesDao;

    @Override
    public String getDefaultImage(Long spuId) {
        return spuImagesDao.getDefaultImage(spuId);
    }
}