package com.laughingather.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.coupon.entity.CouponEntity;
import com.laughingather.gulimall.coupon.entity.query.CouponQuery;

import java.util.List;

/**
 * 优惠券逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
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

