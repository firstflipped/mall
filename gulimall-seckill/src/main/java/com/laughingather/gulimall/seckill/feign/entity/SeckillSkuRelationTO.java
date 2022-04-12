package com.laughingather.gulimall.seckill.feign.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 秒杀活动关联商品开放接口传输类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class SeckillSkuRelationTO {

    private Long id;

    /**
     * 活动场次关联id
     */
    private Long promotionSessionId;

    /**
     * 商品id
     */
    private Long skuId;

    /**
     * 秒杀价格
     */
    private BigDecimal seckillPrice;

    /**
     * 秒杀总量
     */
    private Integer seckillCount;

    /**
     * 每人限购数量
     */
    private Integer seckillLimit;

    /**
     * 排序
     */
    private Integer seckillSort;

}

