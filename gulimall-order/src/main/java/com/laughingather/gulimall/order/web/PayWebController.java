package com.laughingather.gulimall.order.web;

import com.alipay.api.AlipayApiException;
import com.laughingather.gulimall.order.config.AlipayTemplate;
import com.laughingather.gulimall.order.entity.dto.PayDTO;
import com.laughingather.gulimall.order.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 订单支付路由
 *
 * @author：laughingather
 * @create：2021-11-09 2021/11/9
 */
@RestController
@RequestMapping("/pay")
public class PayWebController {

    @Resource
    private AlipayTemplate alipayTemplate;
    @Resource
    private OrderService orderService;


    /**
     * 生成支付页
     *
     * @param orderSn
     * @return
     * @throws AlipayApiException
     */
    @GetMapping(produces = "text/html")
    public String payOrder(@RequestParam("order_sn") String orderSn) throws AlipayApiException {
        // 获取支付详情信息
        PayDTO payDTO = orderService.getPayOrderInfo(orderSn);

        String payPage = alipayTemplate.pay(payDTO);
        return payPage;
    }

}
