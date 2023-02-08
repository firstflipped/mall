package com.flipped.mall.order.controller;

import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.order.entity.OrderReturnReasonEntity;
import com.flipped.mall.order.entity.query.OrderReturnReasonQuery;
import com.flipped.mall.order.service.OrderReturnReasonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 退货原因路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("order/order-return-reason")
@Tag(name = "退货原因模块")
public class OrderReturnReasonController {

    @Resource
    private OrderReturnReasonService orderReturnReasonService;

    @GetMapping("/page")
    @Operation(summary = "分页查询退货原因列表")
    public MyResult<MyPage<OrderReturnReasonEntity>> listOrderReturnReasonsWithPage(@ModelAttribute OrderReturnReasonQuery orderReturnReasonQuery) {
        MyPage<OrderReturnReasonEntity> reasonPage = orderReturnReasonService.listOrderReturnReasonsWithPage(orderReturnReasonQuery);
        return MyResult.success(reasonPage);
    }

    @GetMapping("/{rid}")
    @Operation(summary = "查询退货原因详情")
    public MyResult<OrderReturnReasonEntity> getOrderReturnReasonById(@PathVariable("rid") Long reasonId) {
        OrderReturnReasonEntity reason = orderReturnReasonService.getById(reasonId);
        return MyResult.success(reason);
    }

    @PostMapping
    @Operation(summary = "保存退货原因信息")
    public MyResult<Void> saveOrderReturnReason(@RequestBody OrderReturnReasonEntity orderReturnReason) {
        orderReturnReasonService.saveOrderReturnReason(orderReturnReason);
        return MyResult.success();
    }

    @DeleteMapping
    @Operation(summary = "批量删除退货原因信息")
    public MyResult<Void> deleteBatchOrderReturnReason(@RequestBody List<Long> ids) {
        orderReturnReasonService.removeByIds(ids);
        return MyResult.success();
    }

    @PutMapping
    @Operation(summary = "更新退货原因信息")
    public MyResult<Void> updateOrderReturnReason(@RequestBody OrderReturnReasonEntity orderReturnReason) {
        orderReturnReasonService.updateOrderReturnReason(orderReturnReason);
        return MyResult.success();
    }

}