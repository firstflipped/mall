package com.laughingather.gulimall.member.controller;

import com.laughingather.gulimall.member.service.MemberStatisticsInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 会员统计信息
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:33:47
 */
@RestController
@RequestMapping("/member/memberstatisticsinfo")
public class MemberStatisticsInfoController {
    @Resource
    private MemberStatisticsInfoService memberStatisticsInfoService;

}
