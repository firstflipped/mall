package com.laughingather.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.ware.entity.WareOrderTaskEntity;

/**
 * 库存工作单
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface WareOrderTaskService extends IService<WareOrderTaskEntity> {

    /**
     * 根据订单号获取锁定库存清单
     *
     * @param orderSn
     * @return
     */
    WareOrderTaskEntity getWareOrderTaskByOrderSn(String orderSn);
}

