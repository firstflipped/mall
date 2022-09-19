package com.laughingather.gulimall.seckill.entity.dto;

import com.laughingather.gulimall.seckill.feign.entity.SkuInfoDTO;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 秒杀服务redis存储商品详情
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class SecKillSkuRedisDTO {

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
    private BigDecimal secKillPrice;

    /**
     * 秒杀总量
     */
    private Integer secKillCount;

    /**
     * 每人限购数量
     */
    private Integer secKillLimit;

    /**
     * 排序
     */
    private Integer secKillSort;


    private SkuInfoDTO skuInfo;

    /**
     * 活动的开始时间和结束时间
     * 转换为long型字符戳有利于进行时间比对
     */
    private Long startTime;
    private Long endTime;
}

