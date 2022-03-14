package com.laughingather.gulimall.product.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.laughingather.gulimall.product.dao.SpuImagesDao;
import com.laughingather.gulimall.product.entity.SpuImagesEntity;
import com.laughingather.gulimall.product.service.SpuImagesService;

import javax.annotation.Resource;

/**
 * spu图片逻辑实现
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
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