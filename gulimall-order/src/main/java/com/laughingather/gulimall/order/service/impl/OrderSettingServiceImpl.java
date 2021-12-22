package com.laughingather.gulimall.order.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.query.PageQuery;
import com.laughingather.gulimall.order.dao.OrderSettingDao;
import com.laughingather.gulimall.order.entity.OrderSettingEntity;
import com.laughingather.gulimall.order.service.OrderSettingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;


/**
 * 订单配置信息逻辑实现
 *
 * @author laughingather
 */
@Service("orderSettingService")
public class OrderSettingServiceImpl extends ServiceImpl<OrderSettingDao, OrderSettingEntity> implements OrderSettingService {

    @Resource
    private OrderSettingDao orderSettingDao;

    @Override
    public MyPage<OrderSettingEntity> listOrderSettingsWithPage(PageQuery pageQuery) {
        Page<OrderSettingEntity> page = new Page<>(pageQuery.getPageNumber(), pageQuery.getPageSize());
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