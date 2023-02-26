package com.flipped.mall.ware.controller;

import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.ware.entity.PurchaseEntity;
import com.flipped.mall.ware.entity.param.DonePurchaseParam;
import com.flipped.mall.ware.entity.param.MergePurchaseParam;
import com.flipped.mall.ware.entity.query.PurchaseQuery;
import com.flipped.mall.ware.service.PurchaseService;
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
public class PurchaseController {

    @Resource
    private PurchaseService purchaseService;

    @GetMapping("/page")
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
    public MyResult<List<PurchaseEntity>> listUnReceivePurchaseDetail() {
        List<PurchaseEntity> unReceivePurchaseList = purchaseService.listUnReceivePurchaseDetail();
        return MyResult.success(unReceivePurchaseList);
    }

    @GetMapping("/{pid}")
    public MyResult<PurchaseEntity> getPurchaseById(@PathVariable("pid") Long purchaseId) {
        PurchaseEntity purchase = purchaseService.getById(purchaseId);
        return MyResult.success(purchase);
    }

    @PostMapping
    public MyResult<Void> savePurchase(@RequestBody PurchaseEntity purchase) {
        purchaseService.savePurchase(purchase);
        return MyResult.success();
    }

    @DeleteMapping
    public MyResult<Void> deleteBatchPurchaseByIds(@RequestBody Long[] ids) {
        purchaseService.removeByIds(Arrays.asList(ids));
        return MyResult.success();
    }

    @PutMapping
    public MyResult<Void> updatePurchaseById(@RequestBody PurchaseEntity purchase) {
        purchaseService.updatePurchaseById(purchase);
        return MyResult.success();
    }

    @PutMapping("/merge")
    public MyResult<Void> mergePurchase(@RequestBody MergePurchaseParam mergePurchaseParam) {
        purchaseService.mergePurchase(mergePurchaseParam);
        return MyResult.success();
    }

    @PutMapping("/received")
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
