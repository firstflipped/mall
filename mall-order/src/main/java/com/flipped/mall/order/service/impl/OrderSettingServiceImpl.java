package com.flipped.mall.order.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.common.entity.query.PageQuery;
import com.flipped.mall.order.dao.OrderSettingDao;
import com.flipped.mall.order.entity.OrderSettingEntity;
import com.flipped.mall.order.service.OrderSettingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;


/**
 * 订单配置信息逻辑实现
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Service("orderSettingService")
public class OrderSettingServiceImpl extends ServiceImpl<OrderSettingDao, OrderSettingEntity> implements OrderSettingService {

    @Resource
    private OrderSettingDao orderSettingDao;

    @Override
    public MyPage<OrderSettingEntity> listOrderSettingsWithPage(PageQuery pageQuery) {
        Page<OrderSettingEntity> page = new Page<>(pageQuery.getPn(), pageQuery.getPs());
        Page<OrderSettingEntity> orderSettingPage = orderSettingDao.selectPage(page, null);

        return MyPage.restPage(orderSettingPage);
    }


    @Override
    public void saveOrderSetting(OrderSettingEntity orderSetting) {
        orderSetting.setCreateTime(LocalDateTime.now());

        orderSettingDao.insert(orderSetting);
    }


    @Override
    public void updateOrderSetting(OrderSettingEntity orderSetting) {
        orderSetting.setUpdateTime(LocalDateTime.now());

        orderSettingDao.updateById(orderSetting);
    }
}