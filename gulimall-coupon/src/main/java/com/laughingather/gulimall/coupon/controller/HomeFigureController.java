package com.laughingather.gulimall.coupon.controller;

import com.laughingather.gulimall.coupon.service.HomeFigureService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 首页轮播广告
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/coupon/home-figure")
public class HomeFigureController {

    @Resource
    private HomeFigureService homeFigureService;

}
