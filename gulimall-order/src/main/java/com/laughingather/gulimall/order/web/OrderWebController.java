package com.laughingather.gulimall.order.web;

import com.laughingather.gulimall.common.utils.JsonUtil;
import com.laughingather.gulimall.order.entity.vo.OrderConfirmVO;
import com.laughingather.gulimall.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ExecutionException;

/**
 * 订单页面路由
 *
 * @author：laughingather
 * @create：2021-10-16 2021/10/16
 */

@Controller
@RequestMapping("/order")
public class OrderWebController {

    @Autowired
    private OrderService orderService;

    /**
     * 订单确认页
     *
     * @param model
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping("/to-trade")
    public String toTrade(Model model) throws ExecutionException, InterruptedException {
        OrderConfirmVO orderConfirmVO = orderService.confirmOrder();
        System.out.println(JsonUtil.obj2String(orderConfirmVO));
        model.addAttribute("orderConfirm", orderConfirmVO);
        return "confirm";
    }


}

