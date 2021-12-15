package com.laughingather.gulimall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.laughingather.gulimall.product.entity.SkuInfoEntity;
import com.laughingather.gulimall.product.entity.query.SkuInfoQuery;
import org.apache.ibatis.annotations.Param;

/**
 * sku信息
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
public interface SkuInfoDao extends BaseMapper<SkuInfoEntity> {

    /**
     * 分页查询sku列表
     *
     * @param page
     * @param skuInfoQuery
     * @return
     */
    IPage<SkuInfoEntity> listSkusWithPage(IPage<SkuInfoEntity> page, @Param("params") SkuInfoQuery skuInfoQuery);
}
