package com.laughingather.gulimall.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.order.dao.OrderOperateHistoryDao;
import com.laughingather.gulimall.order.entity.OrderOperateHistoryEntity;
import com.laughingather.gulimall.order.service.OrderOperateHistoryService;
import org.springframework.stereotype.Service;


@Service("orderOperateHistoryService")
public class OrderOperateHistoryServiceImpl extends ServiceImpl<OrderOperateHistoryDao, OrderOperateHistoryEntity> implements OrderOperateHistoryService {

}