package com.laughingather.gulimall.coupon.controller;

import com.laughingather.gulimall.coupon.service.HomeSubjectService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/coupon/home-subject")
public class HomeSubjectController {

    @Resource
    private HomeSubjectService homeSubjectService;

}
