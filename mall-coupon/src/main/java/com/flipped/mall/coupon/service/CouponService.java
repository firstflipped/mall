package com.flipped.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.coupon.entity.CouponEntity;
import com.flipped.mall.coupon.entity.query.CouponQuery;

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

