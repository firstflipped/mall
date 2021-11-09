package com.laughingather.gulimall.order.entity.dto;

import lombok.Data;

/**
 * 支付传输类
 *
 * @author laughingather
 */
@Data
public class PayDTO {
    /**
     * 商户订单号 必填
     */
    private String out_trade_no;

    /**
     * 订单名称 必填
     */
    private String subject;

    /**
     * 订单总金额 必填
     */
    private String total_amount;

    /**
     * 商品描述 可空
     */
    private String body;
}
