package com.laughingather.gulimall.order.controller;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.order.entity.OrderOperateHistoryEntity;
import com.laughingather.gulimall.order.service.OrderOperateHistoryService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * 订单操作历史记录
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("order/order-operate-history")
@Api(tags = "订单操作历史模块")
public class OrderOperateHistoryController {

    @Resource
    private OrderOperateHistoryService orderOperateHistoryService;


    @GetMapping("/{oid}/history")
    public MyResult<List<OrderOperateHistoryEntity>> listHistoriesByOrderId(@PathVariable("oid") Long orderId) {
        List<OrderOperateHistoryEntity> histories = orderOperateHistoryService.listHistoriesByOrderId(orderId);
        return MyResult.success(histories);
    }


}
