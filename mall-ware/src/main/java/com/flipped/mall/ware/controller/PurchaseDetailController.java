package com.flipped.mall.ware.controller;

import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.ware.entity.PurchaseDetailEntity;
import com.flipped.mall.ware.entity.query.PurchaseDetailQuery;
import com.flipped.mall.ware.service.PurchaseDetailService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/ware/purchase-detail")
public class PurchaseDetailController {

    @Resource
    private PurchaseDetailService purchaseDetailService;

    @GetMapping("/page")
    public MyResult<MyPage<PurchaseDetailEntity>> listPurchaseDetailsWithPage(@ModelAttribute PurchaseDetailQuery purchaseDetailQuery) {
        MyPage<PurchaseDetailEntity> purchaseDetailMyPage = purchaseDetailService.listPurchaseDetailsWithPage(purchaseDetailQuery);
        return MyResult.success(purchaseDetailMyPage);
    }

    @GetMapping("/{id}")
    public MyResult<PurchaseDetailEntity> getPurchaseDetailById(@PathVariable("id") Long id) {
        PurchaseDetailEntity purchaseDetail = purchaseDetailService.getById(id);
        return MyResult.success(purchaseDetail);
    }

    @PostMapping
    public MyResult<Void> savePurchaseDetail(@RequestBody PurchaseDetailEntity purchaseDetail) {
        purchaseDetailService.save(purchaseDetail);
        return MyResult.success();
    }

    @DeleteMapping
    public MyResult<Void> deleteBatchPurchaseDetailByIds(@RequestBody Long[] ids) {
        purchaseDetailService.removeByIds(Arrays.asList(ids));
        return MyResult.success();
    }

    @PutMapping
    public MyResult<Void> updatePurchaseDetailById(@RequestBody PurchaseDetailEntity purchaseDetail) {
        purchaseDetailService.updateById(purchaseDetail);
        return MyResult.success();
    }

}
