package com.laughingather.gulimall.product.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.product.entity.SkuInfoEntity;
import com.laughingather.gulimall.product.entity.query.SkuInfoQuery;
import com.laughingather.gulimall.product.service.SkuInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "sku模块")
public class SkuInfoController {

    @Resource
    private SkuInfoService skuInfoService;

    @GetMapping("/page")
    @ApiOperation("分页查询sku列表")
    public MyResult<MyPage<SkuInfoEntity>> listSkusWithPage(@ModelAttribute SkuInfoQuery skuInfoQuery) {
        MyPage<SkuInfoEntity> skuInfoWithPage = skuInfoService.listSkusWithPage(skuInfoQuery);
        return MyResult.success(skuInfoWithPage);
    }

}
