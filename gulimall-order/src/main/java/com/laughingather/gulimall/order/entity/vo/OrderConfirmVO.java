package com.laughingather.gulimall.order.entity.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单确认页实体
 *
 * @author：laughingather
 * @create：2021-10-18 2021/10/18
 */
@ToString
public class OrderConfirmVO {

    /**
     * 收货地址列表
     */
    @Getter
    @Setter
    private List<MemberReceiveAddressVO> addresses;

    /**
     * 所有选中的购物项
     */
    @Getter
    @Setter
    private List<OrderItemVO> items;

    /**
     * 发票信息
     */

    /**
     * 优惠券信息
     * 积分信息，其他优惠暂不展示
     */
    @Getter
    @Setter
    private Integer integration;

    /**
     * 防重令牌
     */
    @Getter
    @Setter
    private String orderToken;

    /**
     * 订单总金额
     */
    private BigDecimal totalPrice;

    /**
     * 应付金额
     */
    private BigDecimal payPrice;

    private Integer totalCount;

    public BigDecimal getTotalPrice() {
        BigDecimal sum = new BigDecimal("0");

        if (CollectionUtils.isNotEmpty(items)) {
            for (OrderItemVO item : items) {
                BigDecimal multiply = item.getPrice().multiply(new BigDecimal(item.getCount()));
                sum = sum.add(multiply);
            }
        }
        return sum;
    }

    public BigDecimal getPayPrice() {
        return getTotalPrice();
    }

    public Integer getTotalCount() {
        Integer count = 0;

        if (CollectionUtils.isNotEmpty(items)) {
            for (OrderItemVO item : items) {
                count += item.getCount();
            }
        }
        return count;
    }
}

