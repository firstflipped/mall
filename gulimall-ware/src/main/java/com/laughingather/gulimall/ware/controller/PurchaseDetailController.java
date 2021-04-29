package com.laughingather.gulimall.ware.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.ware.entity.PurchaseDetailEntity;
import com.laughingather.gulimall.ware.entity.query.PurchaseDetailQuery;
import com.laughingather.gulimall.ware.service.PurchaseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:57:23
 */
@RestController
@RequestMapping("/ware/purchasedetail")
public class PurchaseDetailController {
    @Autowired
    private PurchaseDetailService purchaseDetailService;

    @GetMapping("/page")
    public MyResult<MyPage<PurchaseDetailEntity>> pagePurchaseDetailByParams(@ModelAttribute PurchaseDetailQuery purchaseDetailQuery) {
        MyPage<PurchaseDetailEntity> purchaseDetailMyPage = purchaseDetailService.pagePurchaseDetailByParams(purchaseDetailQuery);
        return MyResult.success(purchaseDetailMyPage);
    }

    @GetMapping("/{id}")
    public MyResult<PurchaseDetailEntity> getPurchaseById(@PathVariable("id") Long id) {
        PurchaseDetailEntity purchaseDetail = purchaseDetailService.getById(id);
        return MyResult.success(purchaseDetail);
    }

    @PostMapping
    public MyResult savePurchase(@RequestBody PurchaseDetailEntity purchaseDetail) {
        purchaseDetailService.save(purchaseDetail);
        return MyResult.success();
    }

    @DeleteMapping
    public MyResult deletePurchaseByIds(@RequestBody Long[] ids) {
        purchaseDetailService.removeByIds(Arrays.asList(ids));
        return MyResult.success();
    }

    @PutMapping
    public MyResult updatePurchaseById(@RequestBody PurchaseDetailEntity purchaseDetail) {
        purchaseDetailService.updateById(purchaseDetail);
        return MyResult.success();
    }

}
