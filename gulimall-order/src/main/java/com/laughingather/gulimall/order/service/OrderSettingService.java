package com.laughingather.gulimall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.query.PageQuery;
import com.laughingather.gulimall.order.entity.OrderSettingEntity;

/**
 * 订单配置信息逻辑接口
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:17:55
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

