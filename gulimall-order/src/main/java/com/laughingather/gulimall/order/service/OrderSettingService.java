package com.laughingather.gulimall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.entity.api.MyPage;
import com.laughingather.gulimall.common.entity.query.PageQuery;
import com.laughingather.gulimall.order.entity.OrderSettingEntity;

/**
 * 订单配置信息逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface OrderSettingService extends IService<OrderSettingEntity> {

    /**
     * 分页查询订单配置信息列表
     *
     * @param pageQuery
     * @return
     */
    MyPage<OrderSettingEntity> listOrderSettingsWithPage(PageQuery pageQuery);

    /**
     * 保存订单配置信息
     *
     * @param orderSetting
     */
    void saveOrderSetting(OrderSettingEntity orderSetting);

    /**
     * 更新订单配置信息
     *
     * @param orderSetting
     */
    void updateOrderSetting(OrderSettingEntity orderSetting);
}

