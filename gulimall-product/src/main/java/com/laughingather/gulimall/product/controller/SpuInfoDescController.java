package com.laughingather.gulimall.product.controller;

import com.laughingather.gulimall.product.service.SpuInfoDescService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * spu信息介绍
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:48
 */
@RestController
@RequestMapping("product/spu-info-desc")
public class SpuInfoDescController {
    @Resource
    private SpuInfoDescService spuInfoDescService;
}
