package com.laughingather.gulimall.coupon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.coupon.dao.HomeSubjectSpuDao;
import com.laughingather.gulimall.coupon.entity.HomeSubjectSpuEntity;
import com.laughingather.gulimall.coupon.service.HomeSubjectSpuService;
import org.springframework.stereotype.Service;


@Service("homeSubjectSpuService")
public class HomeSubjectSpuServiceImpl extends ServiceImpl<HomeSubjectSpuDao, HomeSubjectSpuEntity> implements HomeSubjectSpuService {
}