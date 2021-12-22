package com.laughingather.gulimall.member.controller;

import com.laughingather.gulimall.member.service.GrowthChangeHistoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 成长值变化历史记录
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:33:47
 */
@RestController
@RequestMapping("/member/growth-change-history")
public class GrowthChangeHistoryController {

    @Resource
    private GrowthChangeHistoryService growthChangeHistoryService;

}
