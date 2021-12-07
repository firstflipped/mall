package com.laughingather.gulimall.product.controller;

import com.laughingather.gulimall.product.service.SkuImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * sku图片
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
@RestController
@RequestMapping("product/skuimages")
public class SkuImagesController {
    @Resource
    private SkuImagesService skuImagesService;

}
