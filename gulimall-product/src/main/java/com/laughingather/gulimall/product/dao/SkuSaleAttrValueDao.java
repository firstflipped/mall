package com.laughingather.gulimall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laughingather.gulimall.product.entity.SkuSaleAttrValueEntity;
import com.laughingather.gulimall.product.entity.vo.ItemSaleAttrVO;

import java.util.List;

/**
 * sku销售属性&值
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:48
 */
public interface SkuSaleAttrValueDao extends BaseMapper<SkuSaleAttrValueEntity> {

    /**
     * 根据spuId获取销售属性列表
     *
     * @param spuId
     * @return
     */
    List<ItemSaleAttrVO> getSaleAttrsBySpuId(Long spuId);
}
