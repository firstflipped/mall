package com.laughingather.gulimall.ware.entity.to;

import com.laughingather.gulimall.ware.entity.vo.OrderItemVO;
import lombok.Data;

import java.util.List;

/**
 * 库存锁定传输类
 *
 * @author：laughingather
 * @create：2021-10-20 2021/10/20
 */
@Data
public class WareSkuLockTO {

    /**
     * 订单号
     */
    private String orderSn;

    /**
     * 订单项
     */
    private List<OrderItemVO> locks;

}

