package com.laughingather.gulimall.member.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.member.entity.MemberEntity;
import com.laughingather.gulimall.member.entity.query.MemberQuery;
import com.laughingather.gulimall.member.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


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
    @Resource
    private MemberService memberService;

    @GetMapping("/list")
    public MyResult listMembers(@ModelAttribute MemberQuery memberQuery) {
        IPage<MemberEntity> memberPage = memberService.listMembers(memberQuery);
        return MyResult.success(memberPage);
    }

}
