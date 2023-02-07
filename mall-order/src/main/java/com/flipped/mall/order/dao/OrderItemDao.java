package com.flipped.mall.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flipped.mall.order.entity.OrderItemEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单项信息
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Mapper
public interface OrderItemDao extends BaseMapper<OrderItemEntity> {

}
