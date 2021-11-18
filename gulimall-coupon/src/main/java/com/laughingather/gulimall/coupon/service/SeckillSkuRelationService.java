package com.laughingather.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.coupon.entity.SeckillSkuRelationEntity;
import com.laughingather.gulimall.coupon.entity.query.SeckillSkuRelationQuery;
import com.laughingather.gulimall.coupon.entity.to.SeckillSkuRelationTO;

import java.util.List;

/**
 * 秒杀活动商品关联
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:49:53
 */
public interface SeckillSkuRelationService extends IService<SeckillSkuRelationEntity> {

    /**
     * 获取秒杀活动关联的商品
     *
     * @param seckillSkuRelationQuery
     * @return
     */
    MyPage<SeckillSkuRelationEntity> pageSeckillSkuRelation(SeckillSkuRelationQuery seckillSkuRelationQuery);

    /**
     * 查询秒杀活动关联商品
     *
     * @param promotionSessionId
     * @return
     */
    List<SeckillSkuRelationTO> getRelationSkusByPromotionSessionId(Long promotionSessionId);
}

