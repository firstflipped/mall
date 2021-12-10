package com.laughingather.gulimall.coupon.controller;

import com.laughingather.gulimall.coupon.service.HomeFigureService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 首页轮播广告
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:49:52
 */
@RestController
@RequestMapping("/coupon/home-figure")
public class HomeFigureController {

    @Resource
    private HomeFigureService homeFigureService;

}