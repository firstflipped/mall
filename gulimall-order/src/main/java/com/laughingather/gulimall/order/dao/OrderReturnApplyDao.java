package com.laughingather.gulimall.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laughingather.gulimall.order.entity.OrderReturnApplyEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单退货申请
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Mapper
public interface OrderReturnApplyDao extends BaseMapper<OrderReturnApplyEntity> {

}
