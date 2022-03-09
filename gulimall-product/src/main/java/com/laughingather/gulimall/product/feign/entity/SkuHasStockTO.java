package com.laughingather.gulimall.product.feign.entity;

import lombok.Data;

/**
 * sku库存信息服务传输实体
 *
 * @author：laughingather
 * @date：2021-11-15
 */
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
