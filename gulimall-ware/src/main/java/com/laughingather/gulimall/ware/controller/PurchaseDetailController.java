package com.laughingather.gulimall.ware.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.ware.entity.PurchaseDetailEntity;
import com.laughingather.gulimall.ware.entity.query.PurchaseDetailQuery;
import com.laughingather.gulimall.ware.service.PurchaseDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:57:23
 */
@RestController
@RequestMapping("/ware/purchase-detail")
@Api(tags = "采购信息详情模块")
public class PurchaseDetailController {

    @Resource
    private PurchaseDetailService purchaseDetailService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询采购单详情列表")
    public MyResult<MyPage<PurchaseDetailEntity>> listPurchaseDetailsWithPage(@ModelAttribute PurchaseDetailQuery purchaseDetailQuery) {
        MyPage<PurchaseDetailEntity> purchaseDetailMyPage = purchaseDetailService.listPurchaseDetailsWithPage(purchaseDetailQuery);
        return MyResult.success(purchaseDetailMyPage);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询采购单详情")
    public MyResult<PurchaseDetailEntity> getPurchaseDetailById(@PathVariable("id") Long id) {
        PurchaseDetailEntity purchaseDetail = purchaseDetailService.getById(id);
        return MyResult.success(purchaseDetail);
    }

    @PostMapping
    @ApiOperation(value = "保存采购单详情信息")
    public MyResult savePurchaseDetail(@RequestBody PurchaseDetailEntity purchaseDetail) {
        purchaseDetailService.save(purchaseDetail);
        return MyResult.success();
    }

    @DeleteMapping
    @ApiOperation(value = "批量删除采购单详情信息")
    public MyResult deleteBatchPurchaseDetailByIds(@RequestBody Long[] ids) {
        purchaseDetailService.removeByIds(Arrays.asList(ids));
        return MyResult.success();
    }

    @PutMapping
    @ApiOperation(value = "更新采购单详情信息")
    public MyResult updatePurchaseDetailById(@RequestBody PurchaseDetailEntity purchaseDetail) {
        purchaseDetailService.updateById(purchaseDetail);
        return MyResult.success();
    }

}
