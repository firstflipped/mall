package com.laughingather.gulimall.coupon.controller;

import com.laughingather.gulimall.coupon.service.HomeSubjectSpuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 专题商品
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/coupon/home-subject-spu")
public class HomeSubjectSpuController {

    @Resource
    private HomeSubjectSpuService homeSubjectSpuService;

}
