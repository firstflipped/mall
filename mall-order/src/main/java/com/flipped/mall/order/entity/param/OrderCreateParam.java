package com.flipped.mall.order.entity.param;

import com.flipped.mall.order.entity.OrderEntity;
import com.flipped.mall.order.entity.OrderItemEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单创建所需信息
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class OrderCreateParam {

    /**
     * 订单信息
     */
    private OrderEntity order;

    /**
     * 订单项信息
     */
    private List<OrderItemEntity> orderItems;

    /**
     * 订单应付价格
     */
    private BigDecimal payPrice;

    /**
     * 运费
     */
    private BigDecimal fare;


}

