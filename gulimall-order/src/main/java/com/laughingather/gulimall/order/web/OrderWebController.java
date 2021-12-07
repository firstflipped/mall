package com.laughingather.gulimall.order.web;

import com.laughingather.gulimall.common.api.ResultCodeEnum;
import com.laughingather.gulimall.order.entity.dto.OrderSubmitDTO;
import com.laughingather.gulimall.order.entity.vo.OrderConfirmVO;
import com.laughingather.gulimall.order.entity.vo.OrderSubmitVO;
import com.laughingather.gulimall.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @Resource
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
        model.addAttribute("orderConfirm", orderConfirmVO);
        return "confirm";
    }


    @PostMapping("/submit-order")
    public String submitOrder(OrderSubmitDTO orderSubmitDTO, Model model, RedirectAttributes redirectAttributes) {
        OrderSubmitVO orderSubmitVO = orderService.submitOrder(orderSubmitDTO);

        // 下单成功来到支付选择页
        if (orderSubmitVO.getCode().equals(ResultCodeEnum.SUCCESS.getCode())) {
            model.addAttribute("orderSubmitVO", orderSubmitVO);
            return "pay";
        }
        // 跳转到订单确认页
        else {
            redirectAttributes.addFlashAttribute("message", orderSubmitVO.getMessage());
            return "redirect:http://order.gulimall.com/order/to-trade";
        }
    }

}

