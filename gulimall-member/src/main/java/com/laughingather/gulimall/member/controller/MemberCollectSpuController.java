package com.laughingather.gulimall.member.controller;

import com.laughingather.gulimall.member.service.MemberCollectSpuService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 会员收藏商品路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/member/member-collect-spu")
@Api(tags = "会员收藏商品模块")
public class MemberCollectSpuController {

    @Resource
    private MemberCollectSpuService memberCollectSpuService;

}
