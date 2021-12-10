package com.laughingather.gulimall.member.controller;

import com.laughingather.gulimall.member.service.MemberCollectSubjectService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 会员收藏的专题活动
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:33:47
 */
@RestController
@RequestMapping("/member/member-collect-subject")
public class MemberCollectSubjectController {
    @Resource
    private MemberCollectSubjectService memberCollectSubjectService;
}
