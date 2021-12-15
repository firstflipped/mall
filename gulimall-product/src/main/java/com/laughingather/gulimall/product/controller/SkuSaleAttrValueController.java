package com.laughingather.gulimall.product.controller;

import com.laughingather.gulimall.product.service.SkuSaleAttrValueService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * sku销售属性&值
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:48
 */
@RestController
@RequestMapping("product/sku-sale-attr-value")
public class SkuSaleAttrValueController {

    @Resource
    private SkuSaleAttrValueService skuSaleAttrValueService;
}
