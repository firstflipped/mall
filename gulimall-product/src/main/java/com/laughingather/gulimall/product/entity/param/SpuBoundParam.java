package com.laughingather.gulimall.product.entity.param;

import lombok.Data;

import java.math.BigDecimal;

/**
 * spu积分传入实体
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