package com.flipped.mall.member.controller;

import com.flipped.mall.member.service.MemberCollectSubjectService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 会员收藏专题活动模块
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/member/member-collect-subject")
public class MemberCollectSubjectController {

    @Resource
    private MemberCollectSubjectService memberCollectSubjectService;

}
