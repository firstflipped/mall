package com.laughingather.gulimall.ware.dao;

import com.laughingather.gulimall.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 商品库存
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:57:24
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {

    void addStock(@Param("skuId") Long skuId, @Param("warId") Long wareId, @Param("skuNum") Integer skuNum);

    Integer getCountBySkuIdAndWareId(@Param("skuId") Long skuId, @Param("warId") Long wareId);
}
