package com.laughingather.gulimall.order.controller;

import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.common.entity.api.MyResult;
import com.laughingather.gulimall.order.entity.OrderReturnApplyEntity;
import com.laughingather.gulimall.order.entity.query.OrderReturnApplyQuery;
import com.laughingather.gulimall.order.service.OrderReturnApplyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 订单退货申请
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("order/order-return-apply")
@Tag(name = "订单退货申请模块")
public class OrderReturnApplyController {

    @Resource
    private OrderReturnApplyService orderReturnApplyService;


    @GetMapping("/page")
    @Operation(summary = "分页查询清单退货申请列表")
    public MyResult<MyPage<OrderReturnApplyEntity>> listOrderReturnAppliesWithPage(@ModelAttribute OrderReturnApplyQuery orderReturnApplyQuery) {
        MyPage<OrderReturnApplyEntity> appliesWithPage = orderReturnApplyService.listOrderReturnAppliesWithPage(orderReturnApplyQuery);
        return MyResult.success(appliesWithPage);
    }

}
