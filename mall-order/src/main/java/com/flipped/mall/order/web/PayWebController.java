package com.flipped.mall.order.web;

import com.alipay.api.AlipayApiException;
import com.flipped.mall.order.config.AlipayTemplate;
import com.flipped.mall.order.entity.dto.PayDTO;
import com.flipped.mall.order.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 订单支付路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
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

        return alipayTemplate.pay(payDTO);
    }

}
