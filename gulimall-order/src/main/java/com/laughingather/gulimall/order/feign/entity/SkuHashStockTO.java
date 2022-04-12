package com.laughingather.gulimall.order.feign.entity;

import lombok.Data;

/**
 * 商品库存返回类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class SkuHashStockTO {

    private Long skuId;

    /**
     * 是否有库存
     */
    private Boolean hasStock;

}

