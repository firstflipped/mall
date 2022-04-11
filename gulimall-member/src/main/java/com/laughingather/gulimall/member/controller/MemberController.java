package com.laughingather.gulimall.member.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.member.entity.MemberEntity;
import com.laughingather.gulimall.member.entity.query.MemberQuery;
import com.laughingather.gulimall.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@Api(tags = "会员模块")
public class MemberController {

    @Resource
    private MemberService memberService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询会员列表")
    public MyResult<MyPage<MemberEntity>> listMembersWithPage(@ModelAttribute MemberQuery memberQuery) {
        MyPage<MemberEntity> memberPage = memberService.listMembersWithPage(memberQuery);
        return MyResult.success(memberPage);
    }


}
