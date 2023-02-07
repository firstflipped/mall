package com.flipped.mall.ware.controller;

import com.flipped.mall.ware.service.WareOrderTaskDetailService;
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
@RequestMapping("/ware/ware-order-task-detail")
public class WareOrderTaskDetailController {

    @Resource
    private WareOrderTaskDetailService wareOrderTaskDetailService;

}
