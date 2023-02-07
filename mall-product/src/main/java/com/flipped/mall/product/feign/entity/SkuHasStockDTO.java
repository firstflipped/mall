package com.flipped.mall.product.feign.entity;

import lombok.Data;

/**
 * sku库存信息服务传输实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class SkuHasStockDTO {

    /**
     * skuId
     */
    private Long skuId;

    /**
     * 是否有库存
     */
    private Boolean hasStock;

}
