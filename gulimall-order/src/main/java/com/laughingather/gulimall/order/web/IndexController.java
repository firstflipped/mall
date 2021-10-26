package com.laughingather.gulimall.order.web;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 订单页面路由
 *
 * @author：laughingather
 * @create：2021-10-16 2021/10/16
 */

@Controller
public class IndexController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/{page}.html")
    public String listPage(@PathVariable("page") String page) {
        System.out.println(page);
        return page;
    }
}

