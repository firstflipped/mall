package com.laughingather.gulimall.coupon.dao;

import com.laughingather.gulimall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:49:53
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
