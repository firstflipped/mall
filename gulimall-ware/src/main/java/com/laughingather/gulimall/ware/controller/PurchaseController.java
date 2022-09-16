package com.laughingather.gulimall.ware.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.ware.entity.PurchaseEntity;
import com.laughingather.gulimall.ware.entity.param.DonePurchaseParam;
import com.laughingather.gulimall.ware.entity.param.MergePurchaseParam;
import com.laughingather.gulimall.ware.entity.query.PurchaseQuery;
import com.laughingather.gulimall.ware.service.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


/**
 * 采购信息路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/ware/purchase")
@Tag(name = "采购信息模块")
public class PurchaseController {

    @Resource
    private PurchaseService purchaseService;

    @GetMapping("/page")
    @Operation(summary = "分页查询采购信息列表")
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
    @Operation(summary = "查询新建或者已分配状态的采购单信息列表")
    public MyResult<List<PurchaseEntity>> listUnReceivePurchaseDetail() {
        List<PurchaseEntity> unReceivePurchaseList = purchaseService.listUnReceivePurchaseDetail();
        return MyResult.success(unReceivePurchaseList);
    }

    @GetMapping("/{pid}")
    @Operation(summary = "查询采购单详情")
    public MyResult<PurchaseEntity> getPurchaseById(@PathVariable("pid") Long purchaseId) {
        PurchaseEntity purchase = purchaseService.getById(purchaseId);
        return MyResult.success(purchase);
    }

    @PostMapping
    @Operation(summary = "保存采购单信息")
    public MyResult<Void> savePurchase(@RequestBody PurchaseEntity purchase) {
        purchaseService.savePurchase(purchase);
        return MyResult.success();
    }

    @DeleteMapping
    @Operation(summary = "批量删除采购单信息")
    @Parameter(name = "ids", value = "采购单id集合", dataTypeClass = Long.class)
    public MyResult<Void> deleteBatchPurchaseByIds(@RequestBody Long[] ids) {
        purchaseService.removeByIds(Arrays.asList(ids));
        return MyResult.success();
    }

    @PutMapping
    @Operation(summary = "更新采购单信息")
    public MyResult<Void> updatePurchaseById(@RequestBody PurchaseEntity purchase) {
        purchaseService.updatePurchaseById(purchase);
        return MyResult.success();
    }

    @PutMapping("/merge")
    @Operation(summary = "合并采购单")
    public MyResult<Void> mergePurchase(@RequestBody MergePurchaseParam mergePurchaseParam) {
        purchaseService.mergePurchase(mergePurchaseParam);
        return MyResult.success();
    }

    @PutMapping("/received")
    @Operation(summary = "完成采购单")
    public MyResult<Void> receivedPurchase(@RequestBody List<Long> ids) {
        purchaseService.receivedPurchase(ids);
        return MyResult.success();
    }

    @PutMapping("/done")
    @Operation(summary = "关闭采购单")
    public MyResult<Void> donePurchase(@RequestBody DonePurchaseParam donePurchaseParam) {
        purchaseService.donePurchase(donePurchaseParam);
        return MyResult.success();
    }

}
