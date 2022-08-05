package com.laughingather.gulimall.coupon.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpuBoundDTO {
    private Long spuId;
    private BigDecimal buyBounds;
    private BigDecimal growBounds;
}
