package com.laughingather.gulimall.coupon.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.coupon.entity.SecKillSkuRelationEntity;
import com.laughingather.gulimall.coupon.entity.query.SecKillSkuRelationQuery;
import com.laughingather.gulimall.coupon.service.SecKillSkuRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 秒杀活动商品关联
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/coupon/sec-kill-sku-relation")
@Api(tags = "秒杀活动&商品关联模块")
public class SecKillSkuRelationController {

    @Resource
    private SecKillSkuRelationService secKillSkuRelationService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询秒杀活动&商品关联关系列表")
    public MyResult<MyPage<SecKillSkuRelationEntity>> pageSecKillSkuRelation(@ModelAttribute SecKillSkuRelationQuery secKillSkuRelationQuery) {
        MyPage<SecKillSkuRelationEntity> secKillSkuRelationPage = secKillSkuRelationService.pageSecKillSkuRelation(secKillSkuRelationQuery);
        return MyResult.success(secKillSkuRelationPage);
    }


    @PostMapping
    @ApiOperation(value = "保存秒杀活动&商品关联关系")
    public MyResult<Void> saveSecKillSkuRelation(@RequestBody SecKillSkuRelationEntity secKillSkuRelation) {
        secKillSkuRelationService.save(secKillSkuRelation);
        return MyResult.success();
    }

    @PutMapping
    @ApiOperation(value = "更新秒杀活动&商品关联关系")
    public MyResult<Void> updateSecKillSkuRelation(@RequestBody SecKillSkuRelationEntity secKillSkuRelation) {
        secKillSkuRelationService.updateById(secKillSkuRelation);
        return MyResult.success();
    }


    @DeleteMapping
    @ApiOperation(value = "批量删除秒杀活动&商品关联关系")
    public MyResult<Void> deleteSecKillSkuRelation(@RequestBody List<Long> ids) {
        secKillSkuRelationService.removeByIds(ids);
        return MyResult.success();
    }

}
