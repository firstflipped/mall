package com.laughingather.gulimall.product.feign.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 秒杀服务redis存储商品详情
 *
 * @author：laughingather
 * @create：2021-11-15 2021/11/15
 */
@Data
public class SeckillSkuRedisTO {

    /**
     * 商品id
     */
    private Long skuId;

    /**
     * 商品秒杀随机码
     */
    private String randomCode;

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

    /**
     * 活动的开始时间和结束时间
     * 转换为long型字符戳有利于进行时间比对
     */
    private long startTime;
    private long endTime;
}

