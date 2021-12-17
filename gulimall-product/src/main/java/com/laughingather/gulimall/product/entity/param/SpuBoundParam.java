package com.laughingather.gulimall.product.entity.param;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 积分
 *
 * @author laughingather
 */
@Data
public class SpuBoundParam {

    /**
     * 购买积分
     */
    private BigDecimal buyBounds;

    /**
     * 成长积分
     */
    private BigDecimal growBounds;
}