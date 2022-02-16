package com.laughingather.gulimall.coupon.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.coupon.dao.SecKillPromotionDao;
import com.laughingather.gulimall.coupon.entity.SecKillPromotionEntity;
import com.laughingather.gulimall.coupon.entity.query.SecKillPromotionQuery;
import com.laughingather.gulimall.coupon.service.SecKillPromotionService;
import org.springframework.stereotype.Service;


@Service("secKillPromotionService")
public class SecKillPromotionServiceImpl extends ServiceImpl<SecKillPromotionDao, SecKillPromotionEntity> implements SecKillPromotionService {

    @Override
    public MyPage<SecKillPromotionEntity> pageSecKillPromotion(SecKillPromotionQuery secKillPromotionQuery) {
        IPage<SecKillPromotionEntity> page = new Page<>(secKillPromotionQuery.getPs(), secKillPromotionQuery.getPn());
        IPage<SecKillPromotionEntity> secKillPromotionPage = baseMapper.selectPage(page, null);

        return MyPage.restPage(secKillPromotionPage);
    }
}