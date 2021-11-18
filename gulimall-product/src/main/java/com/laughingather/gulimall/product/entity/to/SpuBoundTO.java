package com.laughingather.gulimall.product.entity.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 传输类
 *
 * @author laughingather
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpuBoundTO {
    private Long spuId;
    private BigDecimal buyBounds;
    private BigDecimal growBounds;
}
