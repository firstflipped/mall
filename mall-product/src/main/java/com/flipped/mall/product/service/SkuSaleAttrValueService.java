package com.flipped.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flipped.mall.product.entity.SkuSaleAttrValueEntity;
import com.flipped.mall.product.entity.vo.ItemSaleAttrVO;

import java.util.List;

/**
 * sku销售属性&值逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface SkuSaleAttrValueService extends IService<SkuSaleAttrValueEntity> {

    /**
     * 根据spuId查询销售属性列表
     *
     * @param spuId spuId
     * @return 销售属性&值VO列表
     */
    List<ItemSaleAttrVO> getSaleAttrsBySpuId(Long spuId);

    /**
     * 根据sku查询销售属性
     *
     * @param skuId skuId
     * @return 销售属性列表
     */
    List<String> getSkuSaleAttrValuesAsString(Long skuId);
}

