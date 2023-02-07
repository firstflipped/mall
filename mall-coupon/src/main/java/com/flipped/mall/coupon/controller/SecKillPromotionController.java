package com.flipped.mall.coupon.controller;

import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.coupon.entity.SecKillPromotionEntity;
import com.flipped.mall.coupon.entity.query.SecKillPromotionQuery;
import com.flipped.mall.coupon.service.SecKillPromotionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;


/**
 * 秒杀活动
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/coupon/sec-kill-promotion")
@Tag(name = "秒杀活动模块")
public class SecKillPromotionController {

    @Resource
    private SecKillPromotionService secKillPromotionService;

    @GetMapping("/page")
    @Operation(summary = "分页查询秒杀活动列表")
    public MyResult<MyPage<SecKillPromotionEntity>> pageSecKillPromotion(@ModelAttribute SecKillPromotionQuery secKillPromotionQuery) {
        MyPage<SecKillPromotionEntity> secKillPromotion = secKillPromotionService.pageSecKillPromotion(secKillPromotionQuery);
        return MyResult.success(secKillPromotion);
    }


    @GetMapping("/{id}")
    @Operation(summary = "查询秒杀活动详情")
    public MyResult<SecKillPromotionEntity> getSecKillPromotionById(@PathVariable Long id) {
        SecKillPromotionEntity secKillPromotion = secKillPromotionService.getById(id);
        return MyResult.success(secKillPromotion);
    }


    @PostMapping
    @Operation(summary = "保存秒杀活动信息")
    public MyResult<Void> saveSecKillPromotion(@RequestBody SecKillPromotionEntity secKillPromotion) {
        secKillPromotion.setCreateTime(LocalDateTime.now());
        secKillPromotionService.save(secKillPromotion);
        return MyResult.success();
    }


    @PutMapping
    @Operation(summary = "更新秒杀活动信息")
    public MyResult<Void> updateSecKillPromotion(@RequestBody SecKillPromotionEntity secKillPromotion) {
        secKillPromotionService.updateById(secKillPromotion);
        return MyResult.success();
    }

}
