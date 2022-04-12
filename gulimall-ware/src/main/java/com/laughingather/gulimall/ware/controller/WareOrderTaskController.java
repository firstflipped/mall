package com.laughingather.gulimall.ware.controller;

import com.laughingather.gulimall.ware.service.WareOrderTaskService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 库存工作单
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/ware/ware-order-task")
public class WareOrderTaskController {

    @Resource
    private WareOrderTaskService wareOrderTaskService;

}
