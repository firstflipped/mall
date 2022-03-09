package com.laughingather.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.product.entity.SkuSaleAttrValueEntity;
import com.laughingather.gulimall.product.entity.vo.ItemSaleAttrVO;

import java.util.List;

/**
 * sku销售属性&值逻辑接口
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:48
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

