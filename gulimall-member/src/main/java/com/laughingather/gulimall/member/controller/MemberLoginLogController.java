package com.laughingather.gulimall.member.controller;

import com.laughingather.gulimall.member.service.MemberLoginLogService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 会员登录记录路由
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:33:47
 */
@RestController
@RequestMapping("/member/member-login-log")
@Api(tags = "会员登录记录模块")
public class MemberLoginLogController {

    @Resource
    private MemberLoginLogService memberLoginLogService;

}
