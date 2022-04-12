package com.laughingather.gulimall.seckill.service.impl;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.common.constant.SecKillConstants;
import com.laughingather.gulimall.seckill.entity.SecKillSkuRedisTO;
import com.laughingather.gulimall.seckill.feign.entity.SeckillSessionTO;
import com.laughingather.gulimall.seckill.feign.entity.SkuInfoTO;
import com.laughingather.gulimall.seckill.feign.service.CouponFeignService;
import com.laughingather.gulimall.seckill.feign.service.ProductFeignService;
import com.laughingather.gulimall.seckill.service.SecKillSkuService;
import org.apache.commons.collections4.CollectionUtils;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 秒杀商品逻辑实现
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Service
public class SecKillSkuServiceImpl implements SecKillSkuService {

    @Resource
    private CouponFeignService couponFeignService;
    @Resource
    private ProductFeignService productFeignService;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private RedissonClient redissonClient;

    @Override
    public void uploadSecKillSkuLatest3Days() {
        MyResult<List<SeckillSessionTO>> last3DaysSession = couponFeignService.getLast3DaysSession();
        if (last3DaysSession.isSuccess()) {
            List<SeckillSessionTO> secKillSessionTOList = last3DaysSession.getData();

            // 将秒杀商品添加到缓存中
            saveSessionsInfo(secKillSessionTOList);
            saveSessionSkusInfo(secKillSessionTOList);
        }
    }

    @Override
    public List<SecKillSkuRedisTO> getCurrentSecKillSkus() {
        List<SecKillSkuRedisTO> result = new ArrayList<>();

        // 确定当前时间属于哪个秒杀场次
        long now = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Set<String> keys = redisTemplate.keys(SecKillConstants.SESSION_CACHE_PREFIX + "*");
        for (String key : keys) {
            String during = key.replace(SecKillConstants.SESSION_CACHE_PREFIX, "");
            String[] time = during.split("-");
            long startTime = Long.parseLong(time[0]);
            long endTime = Long.parseLong(time[1]);

            // 获取这个场次下的所有商品信息
            if (now >= startTime && now < endTime) {
                List<String> skuIds = redisTemplate.opsForList().range(key, -100, 100);
                BoundHashOperations skuOperations = redisTemplate.boundHashOps(SecKillConstants.SESSION_SKUS_CACHE_PREFIX);
                List<SecKillSkuRedisTO> skus = skuOperations.multiGet(skuIds);
                // 当前秒杀开始就需要随机码
                // skus.stream().forEach(sku -> sku.setRandomCode(null));
                result.addAll(skus);
            }
        }


        return result;
    }

    @Override
    public SecKillSkuRedisTO getSecKillSkuInfo(Long skuId) {
        BoundHashOperations<String, String, SecKillSkuRedisTO> skuOperations = redisTemplate.boundHashOps(SecKillConstants.SESSION_SKUS_CACHE_PREFIX);
        Set<String> keys = skuOperations.keys();

        // TODO:此处需要处理同一商品处于不同秒杀场次下的问题
        if (CollectionUtils.isNotEmpty(keys)) {
            String regx = "\\d-" + skuId;
            for (String key : keys) {
                if (Pattern.matches(regx, key)) {
                    SecKillSkuRedisTO seckillSkuRedisTO = skuOperations.get(key);

                    long now = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
                    // 如果已经过了秒杀时间段则直接进行下一次遍历
                    if (now >= seckillSkuRedisTO.getEndTime()) {
                        continue;
                    }

                    // 如果不在秒杀时间内则需要把随机码置空
                    if (now < seckillSkuRedisTO.getStartTime()) {
                        seckillSkuRedisTO.setRandomCode(null);
                    }
                    return seckillSkuRedisTO;
                }
            }
        }

        return null;
    }


    /**
     * 保存秒杀活动信息
     *
     * @param secKillSessionTOList
     */
    private void saveSessionsInfo(List<SeckillSessionTO> secKillSessionTOList) {
        secKillSessionTOList.stream().forEach(secKillSessionTO -> {
            // 返回开始时间和结束时间的时间戳
            long startTime = secKillSessionTO.getStartTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
            long endTime = secKillSessionTO.getEndTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();

            String key = SecKillConstants.SESSION_CACHE_PREFIX + startTime + "-" + endTime;
            // 如果不存在当前的键才添加
            if (!redisTemplate.hasKey(key)) {
                List<String> skuIds = secKillSessionTO.getSkuRelations().stream().map(skuRelationTO ->
                        skuRelationTO.getPromotionSessionId() + "-" + skuRelationTO.getSkuId()
                ).collect(Collectors.toList());
                redisTemplate.opsForList().leftPushAll(key, skuIds);
            }
        });

    }

    /**
     * 保存秒杀活动商品信息
     *
     * @param secKillSessionTOList
     */
    private void saveSessionSkusInfo(List<SeckillSessionTO> secKillSessionTOList) {
        secKillSessionTOList.stream().forEach(secKillSessionTO -> {
            BoundHashOperations skuOperations = redisTemplate.boundHashOps(SecKillConstants.SESSION_SKUS_CACHE_PREFIX);
            secKillSessionTO.getSkuRelations().stream().forEach(skuRelationTO -> {
                // 判断是否存在该商品的信息
                Boolean has = skuOperations.hasKey(skuRelationTO.getPromotionSessionId() + "-" + skuRelationTO.getSkuId());
                if (!has) {

                    // 商品秒杀信息
                    SecKillSkuRedisTO secKillSkuRedisTO = new SecKillSkuRedisTO();
                    BeanUtils.copyProperties(skuRelationTO, secKillSkuRedisTO);

                    // 商品基本信息
                    MyResult<SkuInfoTO> skuInfoResult = productFeignService.getSkuInfoBySkuId(skuRelationTO.getSkuId());
                    if (skuInfoResult.isSuccess()) {
                        secKillSkuRedisTO.setSkuInfo(skuInfoResult.getData());
                    }

                    // 商品场次时间
                    secKillSkuRedisTO.setStartTime(secKillSessionTO.getStartTime().toInstant(ZoneOffset.of("+8")).toEpochMilli());
                    secKillSkuRedisTO.setEndTime(secKillSessionTO.getEndTime().toInstant(ZoneOffset.of("+8")).toEpochMilli());

                    // 商品随机码
                    String randomCode = UUID.randomUUID().toString().replace("-", "");
                    secKillSkuRedisTO.setRandomCode(randomCode);

                    skuOperations.put(skuRelationTO.getPromotionSessionId() + "-" + skuRelationTO.getSkuId(), secKillSkuRedisTO);

                    // 信号量 商品可以秒杀的数量作为信号量
                    RSemaphore semaphore = redissonClient.getSemaphore(SecKillConstants.SKU_STOCK_SEMAPHORE + randomCode);
                    semaphore.trySetPermits(skuRelationTO.getSeckillCount());
                }
            });
        });
    }

}

