package com.laughingather.gulimall.coupon.entity.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 会员价格传输类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class MemberPriceTO {
    private Long id;
    private String name;
    private BigDecimal price;
}