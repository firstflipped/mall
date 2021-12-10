package com.laughingather.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.coupon.entity.SecKillSkuRelationEntity;
import com.laughingather.gulimall.coupon.entity.query.SecKillSkuRelationQuery;
import com.laughingather.gulimall.coupon.entity.to.SecKillSkuRelationTO;

import java.util.List;

/**
 * 秒杀活动商品关联
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:49:53
 */
public interface SecKillSkuRelationService extends IService<SecKillSkuRelationEntity> {

    /**
     * 获取秒杀活动关联的商品
     *
     * @param secKillSkuRelationQuery
     * @return
     */
    MyPage<SecKillSkuRelationEntity> pageSecKillSkuRelation(SecKillSkuRelationQuery secKillSkuRelationQuery);

    /**
     * 查询秒杀活动关联商品
     *
     * @param promotionSessionId
     * @return
     */
    List<SecKillSkuRelationTO> getRelationSkusByPromotionSessionId(Long promotionSessionId);
}

