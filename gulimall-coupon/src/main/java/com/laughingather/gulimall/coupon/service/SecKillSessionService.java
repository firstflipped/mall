package com.laughingather.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.coupon.entity.SecKillSessionEntity;
import com.laughingather.gulimall.coupon.entity.query.SecKillSessionQuery;
import com.laughingather.gulimall.coupon.entity.to.SecKillSessionTO;

import java.util.List;

/**
 * 秒杀活动场次
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:49:52
 */
public interface SecKillSessionService extends IService<SecKillSessionEntity> {

    /**
     * 分页查询每日秒杀
     *
     * @param secKillSessionQuery
     * @return
     */
    MyPage<SecKillSessionEntity> pageSecKillSession(SecKillSessionQuery secKillSessionQuery);

    /**
     * 获取最近三天的秒杀活动列表
     *
     * @return
     */
    List<SecKillSessionTO> getLast3DaysSession();
}

