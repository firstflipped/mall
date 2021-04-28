package com.laughingather.gulimall.coupon.entity.to;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SkuOtherInfoTO {
    private Long skuId;
    private Integer fullCount;
    private BigDecimal discount;
    private Integer countStatus;
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private Integer priceStatus;
    private List<MemberPrice> memberPrice;
}
