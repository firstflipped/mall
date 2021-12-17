package com.laughingather.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.product.entity.ProductAttrValueEntity;

import java.util.List;

/**
 * spu属性值
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
public interface ProductAttrValueService extends IService<ProductAttrValueEntity> {


    /**
     * 根据spuId查询属性值列表
     *
     * @param spuId
     * @return
     */
    List<ProductAttrValueEntity> listAttrValuesBySpuId(Long spuId);

}

