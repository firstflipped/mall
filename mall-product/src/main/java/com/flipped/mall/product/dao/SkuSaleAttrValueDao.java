package com.flipped.mall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flipped.mall.product.entity.SkuSaleAttrValueEntity;
import com.flipped.mall.product.entity.vo.ItemSaleAttrVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * sku销售属性&值
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface SkuSaleAttrValueDao extends BaseMapper<SkuSaleAttrValueEntity> {

    /**
     * 根据spuId获取销售属性列表
     *
     * @param spuId spuId
     * @return 销售属性&值列表
     */
    List<ItemSaleAttrVO> getSaleAttrsBySpuId(@Param("spuId") Long spuId);

    /**
     * 根据sku获取销售属性
     *
     * @param skuId skuId
     * @return 销售属性列表
     */
    List<String> getSkuSaleAttrValuesAsString(@Param("skuId") Long skuId);
}
