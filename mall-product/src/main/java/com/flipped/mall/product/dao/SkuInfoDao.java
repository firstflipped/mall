package com.flipped.mall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.flipped.mall.product.entity.SkuInfoEntity;
import com.flipped.mall.product.entity.query.SkuInfoQuery;
import org.apache.ibatis.annotations.Param;

/**
 * sku信息
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
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
