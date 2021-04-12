package com.laughingather.gulimall.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.order.dao.OrderReturnReasonDao;
import com.laughingather.gulimall.order.entity.OrderReturnReasonEntity;
import com.laughingather.gulimall.order.service.OrderReturnReasonService;
import org.springframework.stereotype.Service;


@Service("orderReturnReasonService")
public class OrderReturnReasonServiceImpl extends ServiceImpl<OrderReturnReasonDao, OrderReturnReasonEntity> implements OrderReturnReasonService {
}