package com.laughingather.gulimall.order.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.order.entity.OrderReturnApplyEntity;
import com.laughingather.gulimall.order.entity.query.OrderReturnApplyQuery;
import com.laughingather.gulimall.order.service.OrderReturnApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 订单退货申请
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:17:55
 */
@RestController
@RequestMapping("order/order-return-apply")
@Api(tags = "订单退货申请模块")
public class OrderReturnApplyController {

    @Resource
    private OrderReturnApplyService orderReturnApplyService;


    @GetMapping("/page")
    @ApiOperation(value = "分页查询清单退货申请列表")
    public MyResult<MyPage<OrderReturnApplyEntity>> listOrderReturnAppliesWithPage(@ModelAttribute OrderReturnApplyQuery orderReturnApplyQuery) {
        MyPage<OrderReturnApplyEntity> appliesWithPage = orderReturnApplyService.listOrderReturnAppliesWithPage(orderReturnApplyQuery);
        return MyResult.success(appliesWithPage);
    }

}
