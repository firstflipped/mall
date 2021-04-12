package com.laughingather.gulimall.coupon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.coupon.dao.CouponSpuRelationDao;
import com.laughingather.gulimall.coupon.entity.CouponSpuRelationEntity;
import com.laughingather.gulimall.coupon.service.CouponSpuRelationService;
import org.springframework.stereotype.Service;


@Service("couponSpuRelationService")
public class CouponSpuRelationServiceImpl extends ServiceImpl<CouponSpuRelationDao, CouponSpuRelationEntity> implements CouponSpuRelationService {

}