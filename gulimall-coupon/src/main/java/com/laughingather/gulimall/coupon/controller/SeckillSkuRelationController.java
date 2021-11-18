package com.laughingather.gulimall.coupon.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.coupon.entity.SeckillSkuRelationEntity;
import com.laughingather.gulimall.coupon.entity.query.SeckillSkuRelationQuery;
import com.laughingather.gulimall.coupon.service.SeckillSkuRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 秒杀活动商品关联
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:49:53
 */
@RestController
@RequestMapping("/coupon/seckillskurelation")
public class SeckillSkuRelationController {
    @Autowired
    private SeckillSkuRelationService seckillSkuRelationService;

    @GetMapping("/page")
    public MyResult<MyPage<SeckillSkuRelationEntity>> pageSeckillSkuRelation(@ModelAttribute SeckillSkuRelationQuery seckillSkuRelationQuery) {
        MyPage<SeckillSkuRelationEntity> seckillSkuRelationPage = seckillSkuRelationService.pageSeckillSkuRelation(seckillSkuRelationQuery);
        return MyResult.success(seckillSkuRelationPage);
    }


    @PostMapping
    public MyResult saveSeckillSkuRelation(@RequestBody SeckillSkuRelationEntity seckillSkuRelation) {
        seckillSkuRelationService.save(seckillSkuRelation);
        return MyResult.success();
    }

    @PutMapping
    public MyResult updateSeckillSkuRelation(@RequestBody SeckillSkuRelationEntity seckillSkuRelation) {
        seckillSkuRelationService.updateById(seckillSkuRelation);
        return MyResult.success();
    }


    @DeleteMapping
    public MyResult deleteSeckillSkuRelation(@RequestBody List<Long> ids) {
        seckillSkuRelationService.removeByIds(ids);
        return MyResult.success();
    }

}
