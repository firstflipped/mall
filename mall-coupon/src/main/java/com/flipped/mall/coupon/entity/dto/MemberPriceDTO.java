package com.flipped.mall.coupon.entity.dto;

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
public class MemberPriceDTO {
    private Long id;
    private String name;
    private BigDecimal price;
}