package com.laughingather.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.product.entity.ProductAttrValueEntity;

import java.util.List;

/**
 * spu属性值逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface ProductAttrValueService extends IService<ProductAttrValueEntity> {


    /**
     * 根据spuId查询属性值列表
     *
     * @param spuId 商品spuId
     * @return spu属性值列表
     */
    List<ProductAttrValueEntity> listAttrValuesBySpuId(Long spuId);

}

