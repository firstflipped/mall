package com.laughingather.gulimall.member.controller;

import com.laughingather.gulimall.member.service.MemberReceiveAddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 会员收货地址路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/member/member-receive-address")
@Tag(name = "会员收货地址模块")
public class MemberReceiveAddressController {

    @Resource
    private MemberReceiveAddressService memberReceiveAddressService;

}
