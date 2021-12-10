package com.laughingather.gulimall.product.controller;

import com.laughingather.gulimall.product.service.ProductAttrValueService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * spu属性值
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
@RestController
@RequestMapping("product/product-attr-value")
public class ProductAttrValueController {
    @Resource
    private ProductAttrValueService productAttrValueService;

}
