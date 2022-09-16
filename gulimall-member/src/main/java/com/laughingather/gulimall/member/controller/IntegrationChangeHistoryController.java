package com.laughingather.gulimall.member.controller;

import com.laughingather.gulimall.member.service.IntegrationChangeHistoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 积分变化历史记录
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/member/integration-change-history")
@Tag(name = "积分变化历史记录模块")
public class IntegrationChangeHistoryController {

    @Resource
    private IntegrationChangeHistoryService integrationChangeHistoryService;

}