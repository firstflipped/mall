package com.flipped.mall.coupon.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.coupon.dao.SecKillPromotionDao;
import com.flipped.mall.coupon.entity.SecKillPromotionEntity;
import com.flipped.mall.coupon.entity.query.SecKillPromotionQuery;
import com.flipped.mall.coupon.service.SecKillPromotionService;
import org.springframework.stereotype.Service;

/**
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Service("secKillPromotionService")
public class SecKillPromotionServiceImpl extends ServiceImpl<SecKillPromotionDao, SecKillPromotionEntity> implements SecKillPromotionService {

    @Override
    public MyPage<SecKillPromotionEntity> pageSecKillPromotion(SecKillPromotionQuery secKillPromotionQuery) {
        IPage<SecKillPromotionEntity> page = new Page<>(secKillPromotionQuery.getPs(), secKillPromotionQuery.getPn());
        IPage<SecKillPromotionEntity> secKillPromotionPage = baseMapper.selectPage(page, null);

        return MyPage.restPage(secKillPromotionPage);
    }
}