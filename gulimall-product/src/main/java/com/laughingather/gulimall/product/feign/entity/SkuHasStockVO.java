package com.laughingather.gulimall.product.feign.entity;

import lombok.Data;

@Data
public class SkuHasStockVO {

    private Long skuId;
    private Boolean hasStock;

}
