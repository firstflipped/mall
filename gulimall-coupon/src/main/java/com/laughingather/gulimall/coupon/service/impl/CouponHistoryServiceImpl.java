package com.laughingather.gulimall.coupon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.coupon.dao.CouponHistoryDao;
import com.laughingather.gulimall.coupon.entity.CouponHistoryEntity;
import com.laughingather.gulimall.coupon.service.CouponHistoryService;
import org.springframework.stereotype.Service;


@Service("couponHistoryService")
public class CouponHistoryServiceImpl extends ServiceImpl<CouponHistoryDao, CouponHistoryEntity> implements CouponHistoryService {


}