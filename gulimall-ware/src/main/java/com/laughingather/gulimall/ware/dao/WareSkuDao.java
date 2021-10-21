package com.laughingather.gulimall.ware.dao;

import com.laughingather.gulimall.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品库存
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:57:24
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {

    /**
     * 增加库存
     *
     * @param skuId
     * @param wareId
     * @param skuNum
     */
    void addStock(@Param("skuId") Long skuId, @Param("wareId") Long wareId, @Param("skuNum") Integer skuNum);

    /**
     * 获取某一商品库存总数量
     *
     * @param skuId
     * @param wareId
     * @return
     */
    Integer getCountBySkuIdAndWareId(@Param("skuId") Long skuId, @Param("warId") Long wareId);

    /**
     * 查询商品是否还有库存
     *
     * @param skuId
     * @return
     */
    Long getSkusHasStock(@Param("skuId") Long skuId);

    /**
     * 查询该商品有剩余库存的仓库
     *
     * @param skuId
     * @return
     */
    List<Long> listWareIdHasSkuStock(@Param("skuId") Long skuId);

    /**
     * 锁定库存
     *
     * @param skuId
     * @param wareId
     * @param count
     */
    Long lockSkuStock(@Param("skuId") Long skuId, @Param("wareId") Long wareId, @Param("count") Integer count);
}
