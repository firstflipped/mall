package com.flipped.mall.member.controller;

import com.flipped.mall.member.service.MemberLoginLogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 会员登录记录模块
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/member/member-login-log")
public class MemberLoginLogController {

    @Resource
    private MemberLoginLogService memberLoginLogService;

}
