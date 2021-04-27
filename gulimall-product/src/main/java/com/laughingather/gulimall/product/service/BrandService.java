package com.laughingather.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.product.entity.BrandEntity;
import com.laughingather.gulimall.product.entity.query.BrandQuery;

/**
 * 品牌
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
public interface BrandService extends IService<BrandEntity> {

    MyPage<BrandEntity> pageBrandsByParams(BrandQuery brandQuery);

    boolean updateBrandById(BrandEntity brand);
}

