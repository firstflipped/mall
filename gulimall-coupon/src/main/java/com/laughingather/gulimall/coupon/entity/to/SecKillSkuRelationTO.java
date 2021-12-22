package com.laughingather.gulimall.coupon.entity.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 秒杀活动关联商品开放接口传输类
 *
 * @author：laughingather
 * @create：2021-11-12 2021/11/12
 */
@Data
public class SecKillSkuRelationTO {

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

}

