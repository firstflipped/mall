package com.flipped.mall.order.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 支付传输类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
