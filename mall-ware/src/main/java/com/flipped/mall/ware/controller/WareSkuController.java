package com.flipped.mall.ware.controller;

import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.ware.entity.WareSkuEntity;
import com.flipped.mall.ware.entity.query.WareSkuQuery;
import com.flipped.mall.ware.service.WareSkuService;
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
public class WareSkuController {

    @Resource
    private WareSkuService wareSkuService;

    @GetMapping("/page")
    public MyResult<MyPage<WareSkuEntity>> listWareSkusWithPage(@ModelAttribute WareSkuQuery wareSkuQuery) {
        MyPage<WareSkuEntity> wareSkuMyPage = wareSkuService.listWareSkusWithPage(wareSkuQuery);
        return MyResult.success(wareSkuMyPage);
    }

    @GetMapping("/{id}")
    public MyResult<WareSkuEntity> getWareSkuById(@PathVariable("id") Long id) {
        WareSkuEntity wareSku = wareSkuService.getById(id);
        return MyResult.success(wareSku);
    }

    @PostMapping
    public MyResult<Void> saveWareSku(@RequestBody WareSkuEntity wareSku) {
        wareSkuService.saveWareSku(wareSku);
        return MyResult.success();
    }

    @DeleteMapping
    public MyResult<Void> deleteBatchWareSkuByIds(@RequestBody Long[] ids) {
        wareSkuService.removeByIds(Arrays.asList(ids));
        return MyResult.success();
    }

    @PutMapping
    public MyResult<Void> updateWareSkuById(@RequestBody WareSkuEntity wareSku) {
        wareSkuService.updateWareSkuById(wareSku);
        return MyResult.success();
    }

}
