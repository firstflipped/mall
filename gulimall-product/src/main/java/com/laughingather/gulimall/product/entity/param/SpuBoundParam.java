package com.laughingather.gulimall.product.entity.param;

import lombok.Data;

import java.math.BigDecimal;

/**
 * spu积分传入实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
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