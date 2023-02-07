package com.flipped.mall.order.web;

import com.flipped.mall.common.entity.api.ResultCodeEnum;
import com.flipped.mall.order.entity.param.OrderSubmitParam;
import com.flipped.mall.order.entity.vo.OrderConfirmVO;
import com.flipped.mall.order.entity.vo.OrderSubmitVO;
import com.flipped.mall.order.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * 订单页面路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
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
    public String submitOrder(OrderSubmitParam orderSubmitParam, Model model, RedirectAttributes redirectAttributes) {
        OrderSubmitVO orderSubmitVO = orderService.submitOrder(orderSubmitParam);

        // 下单成功来到支付选择页
        if (orderSubmitVO.getCode().equals(ResultCodeEnum.SUCCESS.getCode())) {
            model.addAttribute("orderSubmitVO", orderSubmitVO);
            return "pay";
        }
        // 跳转到订单确认页
        else {
            redirectAttributes.addFlashAttribute("message", orderSubmitVO.getMessage());
            return "redirect:http://order.mall.com/order/to-trade";
        }
    }

}

