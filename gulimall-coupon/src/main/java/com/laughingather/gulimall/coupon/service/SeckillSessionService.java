package com.laughingather.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.coupon.entity.SeckillSessionEntity;
import com.laughingather.gulimall.coupon.entity.query.SeckillSessionQuery;
import com.laughingather.gulimall.coupon.entity.to.SeckillSessionTO;

import java.util.List;

/**
 * 秒杀活动场次
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:49:52
 */
public interface SeckillSessionService extends IService<SeckillSessionEntity> {

    /**
     * 分页查询每日秒杀
     *
     * @param seckillSessionQuery
     * @return
     */
    MyPage<SeckillSessionEntity> pageSeckillSession(SeckillSessionQuery seckillSessionQuery);

    /**
     * 获取最近三天的秒杀活动列表
     *
     * @return
     */
    List<SeckillSessionTO> getLast3DaysSession();
}

