package com.laughingather.gulimall.member.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.member.entity.MemberEntity;
import com.laughingather.gulimall.member.entity.query.MemberQuery;
import com.laughingather.gulimall.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 会员路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/member/member")
@Tag(name = "会员模块")
public class MemberController {

    @Resource
    private MemberService memberService;

    @GetMapping("/page")
    @Operation(summary = "分页查询会员列表")
    public MyResult<MyPage<MemberEntity>> listMembersWithPage(@ModelAttribute MemberQuery memberQuery) {
        MyPage<MemberEntity> memberPage = memberService.listMembersWithPage(memberQuery);
        return MyResult.success(memberPage);
    }


    @GetMapping("/{mid}")
    @Operation(summary = "查询会员详情")
    public MyResult<MemberEntity> getMember(@PathVariable(value = "mid") Long memberId) {
        MemberEntity member = memberService.getById(memberId);
        return MyResult.success(member);
    }


}
