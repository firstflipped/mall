package com.flipped.mall.ware.entity;

import lombok.Data;

import java.util.List;

/**
 * 商品库存类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class SkuWareHasStock {

    private Long skuId;

    private String skuName;

    private Integer count;

    private List<Long> wareIds;

}

