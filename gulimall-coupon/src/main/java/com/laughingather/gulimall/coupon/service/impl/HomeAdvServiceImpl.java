package com.laughingather.gulimall.coupon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.coupon.dao.HomeAdvDao;
import com.laughingather.gulimall.coupon.entity.HomeAdvEntity;
import com.laughingather.gulimall.coupon.service.HomeAdvService;
import org.springframework.stereotype.Service;


@Service("homeAdvService")
public class HomeAdvServiceImpl extends ServiceImpl<HomeAdvDao, HomeAdvEntity> implements HomeAdvService {

}