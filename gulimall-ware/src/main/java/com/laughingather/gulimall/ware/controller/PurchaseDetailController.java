package com.laughingather.gulimall.ware.controller;

import com.laughingather.gulimall.common.entity.api.MyPage;
import com.laughingather.gulimall.common.entity.api.MyResult;
import com.laughingather.gulimall.ware.entity.PurchaseDetailEntity;
import com.laughingather.gulimall.ware.entity.query.PurchaseDetailQuery;
import com.laughingather.gulimall.ware.service.PurchaseDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "采购信息详情模块")
public class PurchaseDetailController {

    @Resource
    private PurchaseDetailService purchaseDetailService;

    @GetMapping("/page")
    @Operation(summary = "分页查询采购单详情列表")
    public MyResult<MyPage<PurchaseDetailEntity>> listPurchaseDetailsWithPage(@ModelAttribute PurchaseDetailQuery purchaseDetailQuery) {
        MyPage<PurchaseDetailEntity> purchaseDetailMyPage = purchaseDetailService.listPurchaseDetailsWithPage(purchaseDetailQuery);
        return MyResult.success(purchaseDetailMyPage);
    }

    @GetMapping("/{id}")
    @Operation(summary = "查询采购单详情")
    public MyResult<PurchaseDetailEntity> getPurchaseDetailById(@PathVariable("id") Long id) {
        PurchaseDetailEntity purchaseDetail = purchaseDetailService.getById(id);
        return MyResult.success(purchaseDetail);
    }

    @PostMapping
    @Operation(summary = "保存采购单详情信息")
    public MyResult<Void> savePurchaseDetail(@RequestBody PurchaseDetailEntity purchaseDetail) {
        purchaseDetailService.save(purchaseDetail);
        return MyResult.success();
    }

    @DeleteMapping
    @Operation(summary = "批量删除采购单详情信息")
    public MyResult<Void> deleteBatchPurchaseDetailByIds(@RequestBody Long[] ids) {
        purchaseDetailService.removeByIds(Arrays.asList(ids));
        return MyResult.success();
    }

    @PutMapping
    @Operation(summary = "更新采购单详情信息")
    public MyResult<Void> updatePurchaseDetailById(@RequestBody PurchaseDetailEntity purchaseDetail) {
        purchaseDetailService.updateById(purchaseDetail);
        return MyResult.success();
    }

}
