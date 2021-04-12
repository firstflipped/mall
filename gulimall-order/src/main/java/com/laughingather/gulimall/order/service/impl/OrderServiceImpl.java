package com.laughingather.gulimall.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.order.dao.OrderDao;
import com.laughingather.gulimall.order.entity.OrderEntity;
import com.laughingather.gulimall.order.service.OrderService;
import org.springframework.stereotype.Service;


@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {

}