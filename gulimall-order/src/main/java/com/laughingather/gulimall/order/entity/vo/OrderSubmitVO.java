package com.laughingather.gulimall.order.entity.vo;

import com.laughingather.gulimall.order.entity.OrderEntity;
import lombok.Data;

/**
 * 订单提交页页返回数据
 *
 * @author：laughingather
 * @create：2021-10-19 2021/10/19
 */
@Data
public class OrderSubmitVO {

    /**
     * 订单信息
     */
    private OrderEntity order;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;

}

