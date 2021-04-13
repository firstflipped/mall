package com.laughingather.gulimall.member.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.laughingather.gulimall.common.api.Result;
import com.laughingather.gulimall.member.entity.MemberEntity;
import com.laughingather.gulimall.member.entity.query.MemberQuery;
import com.laughingather.gulimall.member.feign.CouponFeignService;
import com.laughingather.gulimall.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * 会员
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:33:47
 */
@RestController
@RequestMapping("/member/member")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private CouponFeignService couponFeignService;

    @GetMapping("/coupons")
    public Result listCoupons() {
        Result result = couponFeignService.listCoupons();

        return Result.builder().data(result.getData()).build();
    }

    @GetMapping("/list")
    public Result listMembers(@ModelAttribute MemberQuery memberQuery) {

        // 设置分页减一
        // memberQuery.setPageNumber(memberQuery.getPageNumber() - 1);

        if (memberQuery.getPageNumber() == null || memberQuery.getPageNumber() < 0) {
            memberQuery.setPageNumber(0);
        }

        if (memberQuery.getPageSize() == null || memberQuery.getPageSize() < 0) {
            memberQuery.setPageSize(10);
        }

        IPage<MemberEntity> memberPage = memberService.listMembers(memberQuery);
        return Result.builder().data(memberPage).build();
    }

}
