package com.laughingather.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.coupon.entity.SecKillPromotionEntity;
import com.laughingather.gulimall.coupon.entity.query.SecKillPromotionQuery;

/**
 * 秒杀活动
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:49:52
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
