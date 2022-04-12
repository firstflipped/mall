package com.laughingather.gulimall.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 支付信息表
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
@TableName("oms_payment_info")
public class PaymentInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 订单号（对外业务号）
     */
    private String orderSn;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 支付宝交易流水号
     */
    private String alipayTradeNo;

    /**
     * 支付总金额
     */
    private BigDecimal totalAmount;

    /**
     * 交易内容
     */
    private String subject;

    /**
     * 支付状态
     */
    private String paymentStatus;

    /**
     * 确认时间
     */
    private LocalDateTime confirmTime;

    /**
     * 回调内容
     */
    private String callbackContent;

    /**
     * 回调时间
     */
    private LocalDateTime callbackTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
