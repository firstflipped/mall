package com.laughingather.gulimall.seckill.service;

import com.laughingather.gulimall.seckill.entity.to.SecKillSkuRedisTO;

import java.util.List;

/**
 * 秒杀商品逻辑接口
 *
 * @author：laughingather
 * @create：2021-11-12 2021/11/12
 */
public interface SecKillSkuService {

    /**
     * 上架秒杀商品到购物车
     */
    void uploadSecKillSkuLatest3Days();

    /**
     * 获取参加当前时间段秒杀的商品列表
     *
     * @return
     */
    List<SecKillSkuRedisTO> getCurrentSecKillSkus();

    /**
     * 获取秒杀商品详情信息
     *
     * @param skuId
     * @return
     */
    SecKillSkuRedisTO getSecKillSkuInfo(Long skuId);
}
