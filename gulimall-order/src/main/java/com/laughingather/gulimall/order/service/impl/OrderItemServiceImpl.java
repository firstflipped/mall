package com.laughingather.gulimall.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.order.dao.OrderItemDao;
import com.laughingather.gulimall.order.entity.OrderItemEntity;
import com.laughingather.gulimall.order.service.OrderItemService;
import org.springframework.stereotype.Service;


@Service("orderItemService")
public class OrderItemServiceImpl extends ServiceImpl<OrderItemDao, OrderItemEntity> implements OrderItemService {

}