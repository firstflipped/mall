package com.flipped.mall.coupon.controller;

import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.coupon.entity.MemberPriceEntity;
import com.flipped.mall.coupon.entity.query.MemberPriceQuery;
import com.flipped.mall.coupon.service.MemberPriceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 商品会员价格模块
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/coupon/member-price")
public class MemberPriceController {

    @Resource
    private MemberPriceService memberPriceService;

    @GetMapping("/page")
    public MyResult<MyPage<MemberPriceEntity>> pageMemberPrice(@ModelAttribute MemberPriceQuery memberPriceQuery) {
        MyPage<MemberPriceEntity> memberPricePage = memberPriceService.pageMemberPrice(memberPriceQuery);
        return MyResult.success(memberPricePage);
    }

}
