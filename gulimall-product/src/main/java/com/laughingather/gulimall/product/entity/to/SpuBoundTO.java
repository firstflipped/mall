package com.laughingather.gulimall.product.entity.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * spu积分服务传输实体
 *
 * @author laughingather
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpuBoundTO {
    private Long spuId;

    /**
     * 购买积分
     */
    private BigDecimal buyBounds;

    /**
     * 成长积分
     */
    private BigDecimal growBounds;
}
