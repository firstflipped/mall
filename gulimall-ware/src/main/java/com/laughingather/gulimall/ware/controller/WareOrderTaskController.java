package com.laughingather.gulimall.ware.controller;

import com.laughingather.gulimall.ware.service.WareOrderTaskService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 库存工作单
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:57:23
 */
@RestController
@RequestMapping("/ware/wareordertask")
public class WareOrderTaskController {
    @Resource
    private WareOrderTaskService wareOrderTaskService;

}
