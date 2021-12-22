package com.laughingather.gulimall.order.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.order.entity.OrderReturnReasonEntity;
import com.laughingather.gulimall.order.entity.query.OrderReturnReasonQuery;
import com.laughingather.gulimall.order.service.OrderReturnReasonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 退货原因路由
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:17:55
 */
@RestController
@RequestMapping("order/order-return-reason")
@Api(tags = "退货原因模块")
public class OrderReturnReasonController {

    @Resource
    private OrderReturnReasonService orderReturnReasonService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询退货原因列表")
    public MyResult<MyPage<OrderReturnReasonEntity>> listOrderReturnReasonsWithPage(@ModelAttribute OrderReturnReasonQuery orderReturnReasonQuery) {
        MyPage<OrderReturnReasonEntity> reasonPage = orderReturnReasonService.listOrderReturnReasonsWithPage(orderReturnReasonQuery);
        return MyResult.success(reasonPage);
    }

    @GetMapping("/{rid}")
    @ApiOperation(value = "查询退货原因详情")
    public MyResult<OrderReturnReasonEntity> getOrderReturnReasonById(@PathVariable("rid") Long reasonId) {
        OrderReturnReasonEntity reason = orderReturnReasonService.getById(reasonId);
        return MyResult.success(reason);
    }

    @PostMapping
    @ApiOperation(value = "保存退货原因信息")
    public MyResult saveOrderReturnReason(@RequestBody OrderReturnReasonEntity orderReturnReason) {
        orderReturnReasonService.saveOrderReturnReason(orderReturnReason);
        return MyResult.success();
    }

    @DeleteMapping
    @ApiOperation(value = "批量删除退货原因信息")
    public MyResult deleteBatchOrderReturnReason(@RequestBody List<Long> ids) {
        orderReturnReasonService.removeByIds(ids);
        return MyResult.success();
    }

    @PutMapping
    @ApiOperation(value = "更新退货原因信息")
    public MyResult updateOrderReturnReason(@RequestBody OrderReturnReasonEntity orderReturnReason) {
        orderReturnReasonService.updateOrderReturnReason(orderReturnReason);
        return MyResult.success();
    }

}
