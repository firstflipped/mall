package com.flipped.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.coupon.entity.SecKillSessionEntity;
import com.flipped.mall.coupon.entity.dto.SecKillSessionDTO;
import com.flipped.mall.coupon.entity.query.SecKillSessionQuery;

import java.util.List;

/**
 * 秒杀活动场次
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
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
    List<SecKillSessionDTO> getLast3DaysSession();
}

