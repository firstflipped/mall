package com.laughingather.gulimall.ware.entity.vo;

import lombok.Data;

/**
 * @author laughingather
 */
@Data
public class SkuHasStockVO {

    /**
     * 商品id
     */
    private Long skuId;

    /**
     * 是否有库存
     */
    private Boolean hasStock;

}
