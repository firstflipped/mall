package com.laughingather.gulimall.coupon.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.coupon.entity.SecKillSkuRelationEntity;
import com.laughingather.gulimall.coupon.entity.query.SecKillSkuRelationQuery;
import com.laughingather.gulimall.coupon.service.SecKillSkuRelationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 秒杀活动商品关联
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:49:53
 */
@RestController
@RequestMapping("/coupon/sec-kill-sku-relation")
public class SecKillSkuRelationController {
    @Resource
    private SecKillSkuRelationService secKillSkuRelationService;

    @GetMapping("/page")
    public MyResult<MyPage<SecKillSkuRelationEntity>> pageSecKillSkuRelation(@ModelAttribute SecKillSkuRelationQuery secKillSkuRelationQuery) {
        MyPage<SecKillSkuRelationEntity> secKillSkuRelationPage = secKillSkuRelationService.pageSecKillSkuRelation(secKillSkuRelationQuery);
        return MyResult.success(secKillSkuRelationPage);
    }


    @PostMapping
    public MyResult saveSecKillSkuRelation(@RequestBody SecKillSkuRelationEntity secKillSkuRelation) {
        secKillSkuRelationService.save(secKillSkuRelation);
        return MyResult.success();
    }

    @PutMapping
    public MyResult updateSecKillSkuRelation(@RequestBody SecKillSkuRelationEntity secKillSkuRelation) {
        secKillSkuRelationService.updateById(secKillSkuRelation);
        return MyResult.success();
    }


    @DeleteMapping
    public MyResult deleteSecKillSkuRelation(@RequestBody List<Long> ids) {
        secKillSkuRelationService.removeByIds(ids);
        return MyResult.success();
    }

}
