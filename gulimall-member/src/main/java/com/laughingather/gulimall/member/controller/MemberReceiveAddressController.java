package com.laughingather.gulimall.member.controller;

import com.laughingather.gulimall.member.service.MemberReceiveAddressService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 会员收货地址路由
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:33:47
 */
@RestController
@RequestMapping("/member/member-receive-address")
@Api(value = "会员收货地址模块")
public class MemberReceiveAddressController {

    @Resource
    private MemberReceiveAddressService memberReceiveAddressService;

}
