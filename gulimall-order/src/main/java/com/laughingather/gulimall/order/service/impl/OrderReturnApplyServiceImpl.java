package com.laughingather.gulimall.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.order.dao.OrderReturnApplyDao;
import com.laughingather.gulimall.order.entity.OrderReturnApplyEntity;
import com.laughingather.gulimall.order.entity.query.OrderReturnApplyQuery;
import com.laughingather.gulimall.order.service.OrderReturnApplyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 订单退货申请逻辑实现
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Service("orderReturnApplyService")
public class OrderReturnApplyServiceImpl extends ServiceImpl<OrderReturnApplyDao, OrderReturnApplyEntity> implements OrderReturnApplyService {

    @Resource
    private OrderReturnApplyDao orderReturnApplyDao;

    @Override
    public MyPage<OrderReturnApplyEntity> listOrderReturnAppliesWithPage(OrderReturnApplyQuery orderReturnApplyQuery) {
        IPage<OrderReturnApplyEntity> page = new Page<>(orderReturnApplyQuery.getPn(), orderReturnApplyQuery.getPs());

        LambdaQueryWrapper<OrderReturnApplyEntity> queryWrapper = Wrappers.lambdaQuery(OrderReturnApplyEntity.class);
        if (StringUtils.isNotBlank(orderReturnApplyQuery.getOrderSn())) {
            queryWrapper.eq(OrderReturnApplyEntity::getOrderSn, orderReturnApplyQuery.getOrderSn());
        }
        if (StringUtils.isNotBlank(orderReturnApplyQuery.getReturnPhone())) {
            queryWrapper.eq(OrderReturnApplyEntity::getReturnPhone, orderReturnApplyQuery.getReturnPhone());
        }
        if (orderReturnApplyQuery.getStatus() != null) {
            queryWrapper.eq(OrderReturnApplyEntity::getStatus, orderReturnApplyQuery.getStatus());
        }
        if (orderReturnApplyQuery.getReasonId() != null) {
            queryWrapper.eq(OrderReturnApplyEntity::getReasonId, orderReturnApplyQuery.getReasonId());
        }

        IPage<OrderReturnApplyEntity> orderReturnApplyIPage = orderReturnApplyDao.selectPage(page, queryWrapper);
        return MyPage.restPage(orderReturnApplyIPage);
    }
}