package com.flipped.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.coupon.entity.SecKillPromotionEntity;
import com.flipped.mall.coupon.entity.query.SecKillPromotionQuery;

/**
 * 秒杀活动
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface SecKillPromotionService extends IService<SecKillPromotionEntity> {

    /**
     * 分页查询秒杀促销活动
     *
     * @param secKillPromotionQuery
     * @return
     */
    MyPage<SecKillPromotionEntity> pageSecKillPromotion(SecKillPromotionQuery secKillPromotionQuery);
}

