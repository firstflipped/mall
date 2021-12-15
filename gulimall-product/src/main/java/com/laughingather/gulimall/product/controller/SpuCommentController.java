package com.laughingather.gulimall.product.controller;

import com.laughingather.gulimall.product.service.SpuCommentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 商品评价路由
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
@RestController
@RequestMapping("product/spu-comment")
public class SpuCommentController {

    @Resource
    private SpuCommentService spuCommentService;

}
