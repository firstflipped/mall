package com.laughingather.gulimall.order.feign.entity;

import lombok.Data;

/**
 * 商品库存返回类
 *
 * @author：laughingather
 * @create：2021-10-18 2021/10/18
 */
@Data
public class SkuHashStockTO {

    private Long skuId;

    /**
     * 是否有库存
     */
    private Boolean hasStock;

}

