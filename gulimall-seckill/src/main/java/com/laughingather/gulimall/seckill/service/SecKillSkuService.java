package com.laughingather.gulimall.seckill.service;

import com.laughingather.gulimall.seckill.entity.dto.SecKillSkuRedisDTO;

import java.util.List;

/**
 * 秒杀商品逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
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
    List<SecKillSkuRedisDTO> getCurrentSecKillSkus();

    /**
     * 获取秒杀商品详情信息
     *
     * @param skuId
     * @return
     */
    SecKillSkuRedisDTO getSecKillSkuInfo(Long skuId);
}
