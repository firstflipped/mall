package com.laughingather.gulimall.order.entity.dto;

import com.laughingather.gulimall.order.feign.entity.OrderItemTO;
import lombok.Data;

import java.util.List;

/**
 * 库存锁定传输类
 *
 * @author：laughingather
 * @create：2021-10-20 2021/10/20
 */
@Data
public class WareSkuLockDTO {

    /**
     * 订单号
     */
    private String orderSn;

    /**
     * 订单项
     */
    private List<OrderItemTO> locks;

}

