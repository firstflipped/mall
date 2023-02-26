package com.flipped.mall.member.controller;

import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.member.entity.MemberEntity;
import com.flipped.mall.member.entity.query.MemberQuery;
import com.flipped.mall.member.service.MemberService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 会员模块
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/member/member")
public class MemberController {

    @Resource
    private MemberService memberService;

    /**
     * 分页查询会员列表
     *
     * @param memberQuery 会员列表查询条件
     * @return
     */
    @GetMapping("/page")
    public MyResult<MyPage<MemberEntity>> listMembersWithPage(@ModelAttribute MemberQuery memberQuery) {
        MyPage<MemberEntity> memberPage = memberService.listMembersWithPage(memberQuery);
        return MyResult.success(memberPage);
    }


    @GetMapping("/{mid}")
    public MyResult<MemberEntity> getMember(@PathVariable(value = "mid") Long memberId) {
        MemberEntity member = memberService.getById(memberId);
        return MyResult.success(member);
    }


}
