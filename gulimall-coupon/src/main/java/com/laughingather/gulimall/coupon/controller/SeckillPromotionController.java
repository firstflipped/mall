package com.laughingather.gulimall.coupon.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.coupon.entity.SeckillPromotionEntity;
import com.laughingather.gulimall.coupon.entity.query.SeckillPromotionQuery;
import com.laughingather.gulimall.coupon.service.SeckillPromotionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;


/**
 * 秒杀活动
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:49:52
 */
@RestController
@RequestMapping("/coupon/seckillpromotion")
public class SeckillPromotionController {

    @Resource
    private SeckillPromotionService seckillPromotionService;

    @GetMapping("/page")
    public MyResult<MyPage<SeckillPromotionEntity>> pageSeckillPromotion(@ModelAttribute SeckillPromotionQuery seckillPromotionQuery) {
        MyPage<SeckillPromotionEntity> seckillPromotion = seckillPromotionService.pageSeckillPromotion(seckillPromotionQuery);
        return MyResult.success(seckillPromotion);
    }


    @GetMapping("/info/{id}")
    public MyResult<SeckillPromotionEntity> getSeckillPromotionById(@PathVariable Long id) {
        SeckillPromotionEntity seckillPromotion = seckillPromotionService.getById(id);
        return MyResult.success(seckillPromotion);
    }


    @PostMapping
    public MyResult saveSeckillPromotion(@RequestBody SeckillPromotionEntity seckillPromotion) {
        seckillPromotion.setCreateTime(LocalDateTime.now());
        seckillPromotionService.save(seckillPromotion);
        return MyResult.success();
    }

    @PutMapping
    public MyResult updateSeckillPromotion(@RequestBody SeckillPromotionEntity seckillPromotion) {
        seckillPromotionService.updateById(seckillPromotion);
        return MyResult.success();
    }

}
