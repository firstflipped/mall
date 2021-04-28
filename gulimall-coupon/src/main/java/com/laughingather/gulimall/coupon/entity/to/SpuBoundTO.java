package com.laughingather.gulimall.coupon.entity.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpuBoundTO {
    private Long spuId;
    private BigDecimal buyBounds;
    private BigDecimal growBounds;
}
