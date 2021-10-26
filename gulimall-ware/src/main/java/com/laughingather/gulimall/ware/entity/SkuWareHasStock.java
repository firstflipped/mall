package com.laughingather.gulimall.ware.entity;

import lombok.Data;

import java.util.List;

/**
 * 商品库存类
 *
 * @author：laughingather
 * @create：2021-10-20 2021/10/20
 */
@Data
public class SkuWareHasStock {

    private Long skuId;

    private String skuName;

    private Integer count;

    private List<Long> wareIds;

}

