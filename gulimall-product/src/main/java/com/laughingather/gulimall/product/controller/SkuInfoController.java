package com.laughingather.gulimall.product.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.product.entity.SkuInfoEntity;
import com.laughingather.gulimall.product.entity.query.SkuInfoQuery;
import com.laughingather.gulimall.product.service.SkuInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * sku路由
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
@RestController
@RequestMapping("product/sku-info")
public class SkuInfoController {

    @Resource
    private SkuInfoService skuInfoService;

    @GetMapping("/page")
    public MyResult<MyPage<SkuInfoEntity>> listSkusWithPage(@ModelAttribute SkuInfoQuery skuInfoQuery) {
        MyPage<SkuInfoEntity> skuInfoWithPage = skuInfoService.listSkusWithPage(skuInfoQuery);
        return MyResult.success(skuInfoWithPage);
    }

}
