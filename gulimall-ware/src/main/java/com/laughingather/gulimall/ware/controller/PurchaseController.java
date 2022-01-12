package com.laughingather.gulimall.ware.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.ware.entity.PurchaseEntity;
import com.laughingather.gulimall.ware.entity.param.DonePurchaseParam;
import com.laughingather.gulimall.ware.entity.param.MergePurchaseParam;
import com.laughingather.gulimall.ware.entity.query.PurchaseQuery;
import com.laughingather.gulimall.ware.service.PurchaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


/**
 * 采购信息路由
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:57:24
 */
@RestController
@RequestMapping("/ware/purchase")
@Api(tags = "采购信息模块")
public class PurchaseController {

    @Resource
    private PurchaseService purchaseService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询采购信息列表")
    public MyResult<MyPage<PurchaseEntity>> pagePurchaseDetailByParams(@ModelAttribute PurchaseQuery purchaseQuery) {
        MyPage<PurchaseEntity> purchaseMyPage = purchaseService.listPurchasesWithPage(purchaseQuery);
        return MyResult.success(purchaseMyPage);
    }

    /**
     * 查询新建或者已分配状态的采购单信息
     *
     * @return
     */
    @GetMapping("/unreceived/list")
    @ApiOperation(value = "查询新建或者已分配状态的采购单信息列表")
    public MyResult<List<PurchaseEntity>> listUnReceivePurchaseDetail() {
        List<PurchaseEntity> unReceivePurchaseList = purchaseService.listUnReceivePurchaseDetail();
        return MyResult.success(unReceivePurchaseList);
    }

    @GetMapping("/{pid}")
    @ApiOperation(value = "查询采购单详情")
    public MyResult<PurchaseEntity> getPurchaseById(@PathVariable("pid") Long purchaseId) {
        PurchaseEntity purchase = purchaseService.getById(purchaseId);
        return MyResult.success(purchase);
    }

    @PostMapping
    @ApiOperation(value = "保存采购单信息")
    public MyResult<Void> savePurchase(@RequestBody PurchaseEntity purchase) {
        purchaseService.savePurchase(purchase);
        return MyResult.success();
    }

    @DeleteMapping
    @ApiOperation(value = "批量删除采购单信息")
    @ApiImplicitParam(name = "ids", value = "采购单id集合", dataTypeClass = Long.class)
    public MyResult<Void> deleteBatchPurchaseByIds(@RequestBody Long[] ids) {
        purchaseService.removeByIds(Arrays.asList(ids));
        return MyResult.success();
    }

    @PutMapping
    @ApiOperation(value = "更新采购单信息")
    public MyResult<Void> updatePurchaseById(@RequestBody PurchaseEntity purchase) {
        purchaseService.updatePurchaseById(purchase);
        return MyResult.success();
    }

    @PutMapping("/merge")
    @ApiOperation(value = "合并采购单")
    public MyResult<Void> mergePurchase(@RequestBody MergePurchaseParam mergePurchaseParam) {
        purchaseService.mergePurchase(mergePurchaseParam);
        return MyResult.success();
    }

    @PutMapping("/received")
    @ApiOperation(value = "完成采购单")
    public MyResult<Void> receivedPurchase(@RequestBody List<Long> ids) {
        purchaseService.receivedPurchase(ids);
        return MyResult.success();
    }

    @PutMapping("/done")
    public MyResult<Void> donePurchase(@RequestBody DonePurchaseParam donePurchaseParam) {
        purchaseService.donePurchase(donePurchaseParam);
        return MyResult.success();
    }

}
