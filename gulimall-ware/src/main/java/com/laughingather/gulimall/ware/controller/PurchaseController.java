package com.laughingather.gulimall.ware.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.ware.entity.PurchaseEntity;
import com.laughingather.gulimall.ware.entity.dto.DonePurchaseDTO;
import com.laughingather.gulimall.ware.entity.dto.MergePurchaseDTO;
import com.laughingather.gulimall.ware.entity.query.PurchaseQuery;
import com.laughingather.gulimall.ware.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


/**
 * 采购信息
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:57:24
 */
@RestController
@RequestMapping("/ware/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/page")
    public MyResult<MyPage<PurchaseEntity>> pagePurchaseDetailByParams(@ModelAttribute PurchaseQuery purchaseQuery) {
        MyPage<PurchaseEntity> purchaseMyPage = purchaseService.pagePurchaseByParams(purchaseQuery);
        return MyResult.success(purchaseMyPage);
    }

    /**
     * 查询新建或者已分配状态的采购单信息
     *
     * @return
     */
    @GetMapping("/unreceived/list")
    public MyResult<List<PurchaseEntity>> listUnreceivePurchaseDetail() {
        List<PurchaseEntity> unreceivePurchaseList = purchaseService.listUnreceivePurchaseDetail();
        return MyResult.success(unreceivePurchaseList);
    }

    @GetMapping("/{id}")
    public MyResult<PurchaseEntity> getPurchaseById(@PathVariable("id") Long id) {
        PurchaseEntity purchase = purchaseService.getById(id);
        return MyResult.success(purchase);
    }

    @PostMapping
    public MyResult savePurchase(@RequestBody PurchaseEntity purchase) {
        purchaseService.savePurchase(purchase);
        return MyResult.success();
    }

    @DeleteMapping
    public MyResult deletePurchaseByIds(@RequestBody Long[] ids) {
        purchaseService.removeByIds(Arrays.asList(ids));
        return MyResult.success();
    }

    @PutMapping
    public MyResult updatePurchaseById(@RequestBody PurchaseEntity purchase) {
        purchaseService.updatePurchaseById(purchase);
        return MyResult.success();
    }

    /**
     * 合并采购单
     *
     * @param mergePurchaseDTO
     * @return
     */
    @PutMapping("/merge")
    public MyResult mergePurchase(@RequestBody MergePurchaseDTO mergePurchaseDTO) {
        purchaseService.mergePurchase(mergePurchaseDTO);
        return MyResult.success();
    }

    /**
     * 完成采购单
     *
     * @param ids
     * @return
     */
    @PutMapping("/received")
    public MyResult receivedPurchase(@RequestBody List<Long> ids) {
        purchaseService.receivedPurchase(ids);
        return MyResult.success();
    }

    @PutMapping("/done")
    public MyResult donePurchase(@RequestBody DonePurchaseDTO donePurchaseDTO) {
        purchaseService.donePurchase(donePurchaseDTO);
        return MyResult.success();
    }

}
