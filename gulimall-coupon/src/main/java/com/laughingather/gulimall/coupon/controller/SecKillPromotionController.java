package com.laughingather.gulimall.coupon.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.coupon.entity.SecKillPromotionEntity;
import com.laughingather.gulimall.coupon.entity.query.SecKillPromotionQuery;
import com.laughingather.gulimall.coupon.service.SecKillPromotionService;
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
@RequestMapping("/coupon/sec-kill-promotion")
public class SecKillPromotionController {

    @Resource
    private SecKillPromotionService secKillPromotionService;

    @GetMapping("/page")
    public MyResult<MyPage<SecKillPromotionEntity>> pageSecKillPromotion(@ModelAttribute SecKillPromotionQuery secKillPromotionQuery) {
        MyPage<SecKillPromotionEntity> secKillPromotion = secKillPromotionService.pageSecKillPromotion(secKillPromotionQuery);
        return MyResult.success(secKillPromotion);
    }


    @GetMapping("/info/{id}")
    public MyResult<SecKillPromotionEntity> getSecKillPromotionById(@PathVariable Long id) {
        SecKillPromotionEntity secKillPromotion = secKillPromotionService.getById(id);
        return MyResult.success(secKillPromotion);
    }


    @PostMapping
    public MyResult saveSecKillPromotion(@RequestBody SecKillPromotionEntity secKillPromotion) {
        secKillPromotion.setCreateTime(LocalDateTime.now());
        secKillPromotionService.save(secKillPromotion);
        return MyResult.success();
    }

    @PutMapping
    public MyResult updateSecKillPromotion(@RequestBody SecKillPromotionEntity secKillPromotion) {
        secKillPromotionService.updateById(secKillPromotion);
        return MyResult.success();
    }

}
