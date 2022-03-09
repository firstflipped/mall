package com.laughingather.gulimall.product.entity.param;

import lombok.Data;

import java.math.BigDecimal;

/**
 * sku会员价格传入实体
 *
 * @author laughingather
 */
@Data
public class SkuMemberPriceParam {
    /**
     * 会员等级id
     */
    private Long id;

    /**
     * 会员等级名称
     */
    private String name;

    /**
     * 会员价格
     */
    private BigDecimal price;
}