package com.laughingather.gulimall.ware.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.ware.entity.WareSkuEntity;
import com.laughingather.gulimall.ware.entity.query.WareSkuQuery;
import com.laughingather.gulimall.ware.service.WareSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * 商品库存
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:57:24
 */
@RestController
@RequestMapping("/ware/waresku")
public class WareSkuController {
    @Autowired
    private WareSkuService wareSkuService;

    @GetMapping("/page")
    public MyResult<MyPage<WareSkuEntity>> pageWareSkuByParams(@ModelAttribute WareSkuQuery wareSkuQuery) {
        MyPage<WareSkuEntity> wareSkuMyPage = wareSkuService.pageWareSkuByParams(wareSkuQuery);
        return MyResult.success(wareSkuMyPage);
    }

    @GetMapping("/{id}")
    public MyResult<WareSkuEntity> getWareSkuById(@PathVariable("id") Long id) {
        WareSkuEntity wareSku = wareSkuService.getById(id);
        return MyResult.success(wareSku);
    }

    @PostMapping
    public MyResult saveWareSku(@RequestBody WareSkuEntity wareSku) {
        wareSkuService.save(wareSku);
        return MyResult.success();
    }

    @DeleteMapping
    public MyResult deleteWareSkuByIds(@RequestBody Long[] ids) {
        wareSkuService.removeByIds(Arrays.asList(ids));
        return MyResult.success();
    }

    @PutMapping
    public MyResult updateWareSkuById(@RequestBody WareSkuEntity wareSku) {
        wareSkuService.updateById(wareSku);
        return MyResult.success();
    }

}
