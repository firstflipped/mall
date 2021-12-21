package com.laughingather.gulimall.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.order.dao.OrderOperateHistoryDao;
import com.laughingather.gulimall.order.entity.OrderOperateHistoryEntity;
import com.laughingather.gulimall.order.service.OrderOperateHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 订单操作历史记录逻辑实现
 *
 * @author laughingather
 */
@Service("orderOperateHistoryService")
public class OrderOperateHistoryServiceImpl extends ServiceImpl<OrderOperateHistoryDao, OrderOperateHistoryEntity> implements OrderOperateHistoryService {

    @Resource
    private OrderOperateHistoryDao orderOperateHistoryDao;

    @Override
    public List<OrderOperateHistoryEntity> listHistoriesByOrderId(Long orderId) {
        QueryWrapper<OrderOperateHistoryEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(OrderOperateHistoryEntity::getOrderId, orderId);

        return orderOperateHistoryDao.selectList(queryWrapper);
    }
}