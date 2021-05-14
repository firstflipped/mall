package com.laughingather.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.product.entity.SkuInfoEntity;
import com.laughingather.gulimall.product.entity.query.SkuInfoQuery;

import java.util.List;

/**
 * sku信息
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {


    MyPage<SkuInfoEntity> pageSkuInfoByParams(SkuInfoQuery skuInfoQuery);

    List<SkuInfoEntity> listSkusBySpuId(Long spuId);
}

