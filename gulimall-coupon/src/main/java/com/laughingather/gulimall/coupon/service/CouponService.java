package com.laughingather.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.coupon.entity.CouponEntity;
import com.laughingather.gulimall.coupon.entity.query.CouponQuery;

import java.util.List;

/**
 * 优惠券逻辑接口
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:49:53
 */
public interface CouponService extends IService<CouponEntity> {

    /**
     * 查询优惠券列表
     *
     * @return
     */
    List<CouponEntity> listCoupons();

    /**
     * 分页查询优惠券列表
     *
     * @param couponQuery
     * @return
     */
    MyPage<CouponEntity> listCouponsWithPage(CouponQuery couponQuery);
}

