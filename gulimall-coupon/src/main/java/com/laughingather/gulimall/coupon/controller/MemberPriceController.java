package com.laughingather.gulimall.coupon.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.coupon.entity.MemberPriceEntity;
import com.laughingather.gulimall.coupon.entity.query.MemberPriceQuery;
import com.laughingather.gulimall.coupon.service.MemberPriceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 商品会员价格
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:49:52
 */
@RestController
@RequestMapping("/coupon/memberprice")
public class MemberPriceController {

    @Resource
    private MemberPriceService memberPriceService;

    @GetMapping("/page")
    public MyResult<MyPage<MemberPriceEntity>> pageMemberPrice(@ModelAttribute MemberPriceQuery memberPriceQuery) {
        MyPage<MemberPriceEntity> memberPricePage = memberPriceService.pageMemberPrice(memberPriceQuery);
        return MyResult.success(memberPricePage);
    }

}
