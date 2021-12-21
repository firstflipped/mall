package com.laughingather.gulimall.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.order.dao.OrderItemDao;
import com.laughingather.gulimall.order.entity.OrderItemEntity;
import com.laughingather.gulimall.order.service.OrderItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 订单项逻辑实现
 *
 * @author laughingather
 */
@Service("orderItemService")
public class OrderItemServiceImpl extends ServiceImpl<OrderItemDao, OrderItemEntity> implements OrderItemService {

    @Resource
    private OrderItemDao orderItemDao;

    @Override
    public List<OrderItemEntity> listOrderItemsByOrderSn(String orderSn) {
        QueryWrapper<OrderItemEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(OrderItemEntity::getOrderSn, orderSn);

        return orderItemDao.selectList(queryWrapper);
    }
}