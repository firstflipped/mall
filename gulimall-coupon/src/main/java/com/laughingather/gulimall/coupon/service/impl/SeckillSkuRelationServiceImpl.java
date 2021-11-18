package com.laughingather.gulimall.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.coupon.dao.SeckillSkuRelationDao;
import com.laughingather.gulimall.coupon.entity.SeckillSkuRelationEntity;
import com.laughingather.gulimall.coupon.entity.query.SeckillSkuRelationQuery;
import com.laughingather.gulimall.coupon.entity.to.SeckillSkuRelationTO;
import com.laughingather.gulimall.coupon.service.SeckillSkuRelationService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("seckillSkuRelationService")
public class SeckillSkuRelationServiceImpl extends ServiceImpl<SeckillSkuRelationDao, SeckillSkuRelationEntity> implements SeckillSkuRelationService {

    @Override
    public MyPage<SeckillSkuRelationEntity> pageSeckillSkuRelation(SeckillSkuRelationQuery seckillSkuRelationQuery) {
        IPage<SeckillSkuRelationEntity> page = new Page<>(seckillSkuRelationQuery.getPageNumber(), seckillSkuRelationQuery.getPageSize());
        QueryWrapper<SeckillSkuRelationEntity> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(SeckillSkuRelationEntity::getPromotionSessionId, seckillSkuRelationQuery.getPromotionSessionId());

        IPage<SeckillSkuRelationEntity> seckillSkuRelationPage = baseMapper.selectPage(page, queryWrapper);
        return MyPage.restPage(seckillSkuRelationPage);
    }

    @Override
    public List<SeckillSkuRelationTO> getRelationSkusByPromotionSessionId(Long promotionSessionId) {
        return baseMapper.getRelationSkusByPromotionSessionId(promotionSessionId);
    }


}