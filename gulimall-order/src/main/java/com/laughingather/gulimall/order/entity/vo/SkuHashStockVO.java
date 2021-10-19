package com.laughingather.gulimall.order.entity.vo;

import lombok.Data;

/**
 * 商品库存返回类
 *
 * @author：laughingather
 * @create：2021-10-18 2021/10/18
 */
@Data
public class SkuHashStockVO {

    private Long skuId;
    private Boolean hasStock;

}

