package com.flipped.mall.ware.controller;

import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.ware.entity.WareSkuEntity;
import com.flipped.mall.ware.entity.query.WareSkuQuery;
import com.flipped.mall.ware.service.WareSkuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;


/**
 * 商品库存
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/ware/ware-sku")
@Tag(name = "商品库存模块")
public class WareSkuController {

    @Resource
    private WareSkuService wareSkuService;

    @GetMapping("/page")
    @Operation(summary = "分页查询商品库存列表")
    public MyResult<MyPage<WareSkuEntity>> listWareSkusWithPage(@ModelAttribute WareSkuQuery wareSkuQuery) {
        MyPage<WareSkuEntity> wareSkuMyPage = wareSkuService.listWareSkusWithPage(wareSkuQuery);
        return MyResult.success(wareSkuMyPage);
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取商品库存详情")
    public MyResult<WareSkuEntity> getWareSkuById(@PathVariable("id") Long id) {
        WareSkuEntity wareSku = wareSkuService.getById(id);
        return MyResult.success(wareSku);
    }

    @PostMapping
    @Operation(summary = "保存商品库存信息")
    public MyResult<Void> saveWareSku(@RequestBody WareSkuEntity wareSku) {
        wareSkuService.saveWareSku(wareSku);
        return MyResult.success();
    }

    @DeleteMapping
    @Operation(summary = "批量删除商品库存信息")
    public MyResult<Void> deleteBatchWareSkuByIds(@RequestBody Long[] ids) {
        wareSkuService.removeByIds(Arrays.asList(ids));
        return MyResult.success();
    }

    @PutMapping
    @Operation(summary = "更新商品库存信息")
    public MyResult<Void> updateWareSkuById(@RequestBody WareSkuEntity wareSku) {
        wareSkuService.updateWareSkuById(wareSku);
        return MyResult.success();
    }

}
