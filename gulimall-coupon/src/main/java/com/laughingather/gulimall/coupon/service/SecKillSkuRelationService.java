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
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
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

