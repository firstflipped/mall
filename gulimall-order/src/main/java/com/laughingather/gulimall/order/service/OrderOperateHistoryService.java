package com.laughingather.gulimall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.order.entity.OrderOperateHistoryEntity;

import java.util.List;

/**
 * 订单操作历史记录逻辑接口
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:17:55
 */
public interface OrderOperateHistoryService extends IService<OrderOperateHistoryEntity> {


    /**
     * 根据订单id查询订单操作历史记录列表
     *
     * @param orderId
     * @return
     */
    List<OrderOperateHistoryEntity> listHistoriesByOrderId(Long orderId);
}

