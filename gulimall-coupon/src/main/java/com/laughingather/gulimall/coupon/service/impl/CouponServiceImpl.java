package com.laughingather.gulimall.coupon.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.coupon.dao.CouponDao;
import com.laughingather.gulimall.coupon.entity.CouponEntity;
import com.laughingather.gulimall.coupon.entity.query.CouponQuery;
import com.laughingather.gulimall.coupon.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("couponService")
@Slf4j
public class CouponServiceImpl extends ServiceImpl<CouponDao, CouponEntity> implements CouponService {

    @Resource
    private CouponDao couponDao;

    @Override
    public List<CouponEntity> listCoupons() {
        return couponDao.selectList(null);
    }

    @Override
    public MyPage<CouponEntity> pageCoupons(CouponQuery couponQuery) {
        IPage<CouponEntity> page = new Page<>(couponQuery.getPageNumber(), couponQuery.getPageSize());
        IPage<CouponEntity> couponPage = couponDao.selectPage(page, null);
        return MyPage.restPage(couponPage);
    }
}