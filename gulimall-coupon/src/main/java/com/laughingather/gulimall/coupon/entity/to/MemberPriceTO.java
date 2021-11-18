package com.laughingather.gulimall.coupon.entity.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 会员价格传输类
 *
 * @author laughingather
 */
@Data
public class MemberPriceTO {
    private Long id;
    private String name;
    private BigDecimal price;
}