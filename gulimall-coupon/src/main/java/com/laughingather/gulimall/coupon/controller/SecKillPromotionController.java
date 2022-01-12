package com.laughingather.gulimall.coupon.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.coupon.entity.SecKillPromotionEntity;
import com.laughingather.gulimall.coupon.entity.query.SecKillPromotionQuery;
import com.laughingather.gulimall.coupon.service.SecKillPromotionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "秒杀活动模块")
public class SecKillPromotionController {

    @Resource
    private SecKillPromotionService secKillPromotionService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询秒杀活动列表")
    public MyResult<MyPage<SecKillPromotionEntity>> pageSecKillPromotion(@ModelAttribute SecKillPromotionQuery secKillPromotionQuery) {
        MyPage<SecKillPromotionEntity> secKillPromotion = secKillPromotionService.pageSecKillPromotion(secKillPromotionQuery);
        return MyResult.success(secKillPromotion);
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "查询秒杀活动详情")
    public MyResult<SecKillPromotionEntity> getSecKillPromotionById(@PathVariable Long id) {
        SecKillPromotionEntity secKillPromotion = secKillPromotionService.getById(id);
        return MyResult.success(secKillPromotion);
    }


    @PostMapping
    @ApiOperation(value = "保存秒杀活动信息")
    public MyResult<Void> saveSecKillPromotion(@RequestBody SecKillPromotionEntity secKillPromotion) {
        secKillPromotion.setCreateTime(LocalDateTime.now());
        secKillPromotionService.save(secKillPromotion);
        return MyResult.success();
    }


    @PutMapping
    @ApiOperation(value = "更新秒杀活动信息")
    public MyResult<Void> updateSecKillPromotion(@RequestBody SecKillPromotionEntity secKillPromotion) {
        secKillPromotionService.updateById(secKillPromotion);
        return MyResult.success();
    }

}
