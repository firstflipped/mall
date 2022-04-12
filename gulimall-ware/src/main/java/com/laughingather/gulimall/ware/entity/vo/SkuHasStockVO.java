package com.laughingather.gulimall.ware.entity.vo;

import lombok.Data;

/**
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
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
