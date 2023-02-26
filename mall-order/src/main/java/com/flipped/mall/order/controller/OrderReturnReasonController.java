package com.flipped.mall.order.controller;

import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.order.entity.OrderReturnReasonEntity;
import com.flipped.mall.order.entity.query.OrderReturnReasonQuery;
import com.flipped.mall.order.service.OrderReturnReasonService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 退货原因模块
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("order/order-return-reason")
public class OrderReturnReasonController {

    @Resource
    private OrderReturnReasonService orderReturnReasonService;

    @GetMapping("/page")
    public MyResult<MyPage<OrderReturnReasonEntity>> listOrderReturnReasonsWithPage(@ModelAttribute OrderReturnReasonQuery orderReturnReasonQuery) {
        MyPage<OrderReturnReasonEntity> reasonPage = orderReturnReasonService.listOrderReturnReasonsWithPage(orderReturnReasonQuery);
        return MyResult.success(reasonPage);
    }

    @GetMapping("/{rid}")
    public MyResult<OrderReturnReasonEntity> getOrderReturnReasonById(@PathVariable("rid") Long reasonId) {
        OrderReturnReasonEntity reason = orderReturnReasonService.getById(reasonId);
        return MyResult.success(reason);
    }

    @PostMapping
    public MyResult<Void> saveOrderReturnReason(@RequestBody OrderReturnReasonEntity orderReturnReason) {
        orderReturnReasonService.saveOrderReturnReason(orderReturnReason);
        return MyResult.success();
    }

    @DeleteMapping
    public MyResult<Void> deleteBatchOrderReturnReason(@RequestBody List<Long> ids) {
        orderReturnReasonService.removeByIds(ids);
        return MyResult.success();
    }

    @PutMapping
    public MyResult<Void> updateOrderReturnReason(@RequestBody OrderReturnReasonEntity orderReturnReason) {
        orderReturnReasonService.updateOrderReturnReason(orderReturnReason);
        return MyResult.success();
    }

}
