package com.flipped.mall.member.controller;

import com.flipped.mall.member.service.GrowthChangeHistoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 成长值变化历史记录路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/member/growth-change-history")
@Tag(name = "成长值变化历史记录模块")
public class GrowthChangeHistoryController {

    @Resource
    private GrowthChangeHistoryService growthChangeHistoryService;

}
