package com.laughingather.gulimall.coupon.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.coupon.entity.MemberPriceEntity;
import com.laughingather.gulimall.coupon.entity.query.MemberPriceQuery;
import com.laughingather.gulimall.coupon.service.MemberPriceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 商品会员价格
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/coupon/member-price")
@Api(tags = "商品会员价格模块")
public class MemberPriceController {

    @Resource
    private MemberPriceService memberPriceService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询商品会员价格列表")
    public MyResult<MyPage<MemberPriceEntity>> pageMemberPrice(@ModelAttribute MemberPriceQuery memberPriceQuery) {
        MyPage<MemberPriceEntity> memberPricePage = memberPriceService.pageMemberPrice(memberPriceQuery);
        return MyResult.success(memberPricePage);
    }

}
