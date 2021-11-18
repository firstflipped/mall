package com.laughingather.gulimall.coupon.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.coupon.dao.SeckillPromotionDao;
import com.laughingather.gulimall.coupon.entity.SeckillPromotionEntity;
import com.laughingather.gulimall.coupon.entity.query.SeckillPromotionQuery;
import com.laughingather.gulimall.coupon.service.SeckillPromotionService;
import org.springframework.stereotype.Service;


@Service("seckillPromotionService")
public class SeckillPromotionServiceImpl extends ServiceImpl<SeckillPromotionDao, SeckillPromotionEntity> implements SeckillPromotionService {


    @Override
    public MyPage<SeckillPromotionEntity> pageSeckillPromotion(SeckillPromotionQuery seckillPromotionQuery) {
        IPage<SeckillPromotionEntity> page = new Page<>(seckillPromotionQuery.getPageSize(), seckillPromotionQuery.getPageNumber());
        IPage<SeckillPromotionEntity> seckillPromotionPage = baseMapper.selectPage(page, null);

        return MyPage.restPage(seckillPromotionPage);
    }
}