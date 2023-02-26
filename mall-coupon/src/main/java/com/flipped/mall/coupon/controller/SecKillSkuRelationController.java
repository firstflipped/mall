package com.flipped.mall.coupon.controller;

import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.coupon.entity.SecKillSkuRelationEntity;
import com.flipped.mall.coupon.entity.query.SecKillSkuRelationQuery;
import com.flipped.mall.coupon.service.SecKillSkuRelationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 秒杀活动&商品关联模块
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/coupon/sec-kill-sku-relation")
public class SecKillSkuRelationController {

    @Resource
    private SecKillSkuRelationService secKillSkuRelationService;

    /**
     * 分页查询秒杀活动&商品关联关系列表
     *
     * @param secKillSkuRelationQuery
     * @return
     */
    @GetMapping("/page")
    public MyResult<MyPage<SecKillSkuRelationEntity>> pageSecKillSkuRelation(@ModelAttribute SecKillSkuRelationQuery secKillSkuRelationQuery) {
        MyPage<SecKillSkuRelationEntity> secKillSkuRelationPage = secKillSkuRelationService.pageSecKillSkuRelation(secKillSkuRelationQuery);
        return MyResult.success(secKillSkuRelationPage);
    }

    @PostMapping
    public MyResult<Void> saveSecKillSkuRelation(@RequestBody SecKillSkuRelationEntity secKillSkuRelation) {
        secKillSkuRelationService.save(secKillSkuRelation);
        return MyResult.success();
    }

    @PutMapping
    public MyResult<Void> updateSecKillSkuRelation(@RequestBody SecKillSkuRelationEntity secKillSkuRelation) {
        secKillSkuRelationService.updateById(secKillSkuRelation);
        return MyResult.success();
    }

    @DeleteMapping
    public MyResult<Void> deleteSecKillSkuRelation(@RequestBody List<Long> ids) {
        secKillSkuRelationService.removeByIds(ids);
        return MyResult.success();
    }

}
