package com.laughingather.gulimall.product.feign.entity;

import lombok.Data;

@Data
public class SkuHasStockTO {

    /**
     * skuId
     */
    private Long skuId;

    /**
     * 是否有库存
     */
    private Boolean hasStock;

}
