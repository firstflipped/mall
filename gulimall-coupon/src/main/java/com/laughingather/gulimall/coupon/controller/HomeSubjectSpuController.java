package com.laughingather.gulimall.coupon.controller;

import com.laughingather.gulimall.coupon.service.HomeSubjectSpuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 专题商品
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:49:52
 */
@RestController
@RequestMapping("/coupon/home-subject-spu")
public class HomeSubjectSpuController {

    @Resource
    private HomeSubjectSpuService homeSubjectSpuService;

}
