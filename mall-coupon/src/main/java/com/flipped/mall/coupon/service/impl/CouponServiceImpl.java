package com.flipped.mall.coupon.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.coupon.entity.CouponEntity;
import com.flipped.mall.coupon.dao.CouponDao;
import com.flipped.mall.coupon.entity.query.CouponQuery;
import com.flipped.mall.coupon.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 优惠券逻辑实现
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Slf4j
@Service("couponService")
public class CouponServiceImpl extends ServiceImpl<CouponDao, CouponEntity> implements CouponService {

    @Resource
    private CouponDao couponDao;

    @Override
    public List<CouponEntity> listCoupons() {
        return couponDao.selectList(null);
    }

    @Override
    public MyPage<CouponEntity> listCouponsWithPage(CouponQuery couponQuery) {
        IPage<CouponEntity> page = new Page<>(couponQuery.getPn(), couponQuery.getPs());
        IPage<CouponEntity> couponPage = couponDao.selectPage(page, null);
        return MyPage.restPage(couponPage);
    }
}