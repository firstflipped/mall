package com.laughingather.gulimall.product.controller;

import com.laughingather.gulimall.common.entity.api.MyPage;
import com.laughingather.gulimall.common.entity.api.MyResult;
import com.laughingather.gulimall.product.entity.SkuInfoEntity;
import com.laughingather.gulimall.product.entity.query.SkuInfoQuery;
import com.laughingather.gulimall.product.service.SkuInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * sku路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/product/sku")
@Tag(name = "sku模块")
public class SkuInfoController {

    @Resource
    private SkuInfoService skuInfoService;

    @GetMapping("/page")
    @Operation(summary = "分页查询sku列表")
    public MyResult<MyPage<SkuInfoEntity>> listSkusWithPage(@ModelAttribute SkuInfoQuery skuInfoQuery) {
        MyPage<SkuInfoEntity> skuInfoWithPage = skuInfoService.listSkusWithPage(skuInfoQuery);
        return MyResult.success(skuInfoWithPage);
    }

}
