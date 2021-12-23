package com.laughingather.gulimall.member.controller;

import com.laughingather.gulimall.member.service.MemberCollectSubjectService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 会员收藏专题活动路由
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:33:47
 */
@RestController
@RequestMapping("/member/member-collect-subject")
@Api(tags = "会员收藏专题活动模块")
public class MemberCollectSubjectController {

    @Resource
    private MemberCollectSubjectService memberCollectSubjectService;

}
