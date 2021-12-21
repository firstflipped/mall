package com.laughingather.gulimall.order.entity.param;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单提交前端传入数据
 *
 * @author：laughingather
 * @create：2021-10-19 2021/10/19
 */
@Data
public class OrderSubmitParam {

    /**
     * 收货地址id
     */
    private Long addressId;

    /**
     * 支付方式
     */
    private Integer payType;

    // 无需提交需要购买的商品，实时去购物车再获取一遍即可

    /**
     * 令牌
     */
    private String orderToken;

    /**
     * 应付价格，用于验价
     */
    private BigDecimal payPrice;

    // 用户相关信息直接去session中去取

    /**
     * 订单备注
     */
    private String note;
}

