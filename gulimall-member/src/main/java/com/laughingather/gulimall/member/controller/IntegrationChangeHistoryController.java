package com.laughingather.gulimall.member.controller;

import com.laughingather.gulimall.member.service.IntegrationChangeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * 积分变化历史记录
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:33:47
 */
@RestController
@RequestMapping("/member/integrationchangehistory")
public class IntegrationChangeHistoryController {
    @Resource
    private IntegrationChangeHistoryService integrationChangeHistoryService;

}