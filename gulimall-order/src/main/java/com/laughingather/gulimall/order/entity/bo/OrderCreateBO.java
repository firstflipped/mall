package com.laughingather.gulimall.order.entity.bo;

import com.laughingather.gulimall.order.entity.OrderEntity;
import com.laughingather.gulimall.order.entity.OrderItemEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单创建所需信息
 *
 * @author：laughingather
 * @create：2021-10-20 2021/10/20
 */
@Data
public class OrderCreateBO {

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

