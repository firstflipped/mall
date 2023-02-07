package com.laughingather.gulimall.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flipped.mall.common.entity.api.MyPage;
import com.laughingather.gulimall.order.dao.OrderReturnReasonDao;
import com.laughingather.gulimall.order.entity.OrderReturnReasonEntity;
import com.laughingather.gulimall.order.entity.query.OrderReturnReasonQuery;
import com.laughingather.gulimall.order.service.OrderReturnReasonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;


/**
 * 退货原因逻辑实现
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Service("orderReturnReasonService")
public class OrderReturnReasonServiceImpl extends ServiceImpl<OrderReturnReasonDao, OrderReturnReasonEntity> implements OrderReturnReasonService {

    @Resource
    private OrderReturnReasonDao orderReturnReasonDao;

    @Override
    public MyPage<OrderReturnReasonEntity> listOrderReturnReasonsWithPage(OrderReturnReasonQuery orderReturnReasonQuery) {
        IPage<OrderReturnReasonEntity> page = new Page<>(orderReturnReasonQuery.getPn(), orderReturnReasonQuery.getPs());

        QueryWrapper<OrderReturnReasonEntity> queryWrapper = new QueryWrapper<>();
        if (orderReturnReasonQuery.getStatus() != null) {
            queryWrapper.lambda().eq(OrderReturnReasonEntity::getStatus, orderReturnReasonQuery.getStatus());
        }

        return MyPage.restPage(orderReturnReasonDao.selectPage(page, queryWrapper));
    }

    @Override
    public void saveOrderReturnReason(OrderReturnReasonEntity orderReturnReason) {
        // 默认创建时间
        orderReturnReason.setCreateTime(LocalDateTime.now());

        orderReturnReasonDao.insert(orderReturnReason);
    }

    @Override
    public void updateOrderReturnReason(OrderReturnReasonEntity orderReturnReason) {
        // 默认创建时间
        orderReturnReason.setUpdateTime(LocalDateTime.now());

        orderReturnReasonDao.updateById(orderReturnReason);
    }
}