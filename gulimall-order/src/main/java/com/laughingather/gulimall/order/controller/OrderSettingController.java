package com.laughingather.gulimall.order.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.common.query.PageQuery;
import com.laughingather.gulimall.order.entity.OrderSettingEntity;
import com.laughingather.gulimall.order.service.OrderSettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 订单配置信息路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("order/order-setting")
@Api(tags = "订单配置信息模块")
public class OrderSettingController {

    @Resource
    private OrderSettingService orderSettingService;


    @GetMapping("/page")
    @ApiOperation(value = "分页查询订单配置信息列表")
    public MyResult<MyPage<OrderSettingEntity>> listOrderSettingsWithPage(@ModelAttribute PageQuery pageQuery) {
        MyPage<OrderSettingEntity> orderSettingPage = orderSettingService.listOrderSettingsWithPage(pageQuery);
        return MyResult.success(orderSettingPage);
    }


    @GetMapping("/{sid}")
    @ApiOperation(value = "查询订单配置信息详情")
    public MyResult<OrderSettingEntity> getOrderSetting(@PathVariable("sid") Long id) {
        OrderSettingEntity orderSetting = orderSettingService.getById(id);
        return MyResult.success(orderSetting);
    }


    @PostMapping
    @ApiOperation(value = "保存订单配置信息")
    public MyResult saveOrderSetting(@RequestBody OrderSettingEntity orderSetting) {
        orderSettingService.saveOrderSetting(orderSetting);
        return MyResult.success();
    }


    @PutMapping
    @ApiOperation(value = "更新订单配置信息")
    public MyResult<Void> updateOrderSetting(@RequestBody OrderSettingEntity orderSetting) {
        orderSettingService.updateOrderSetting(orderSetting);
        return MyResult.success();
    }


    @DeleteMapping
    @ApiOperation(value = "批量删除订单配置信息")
    public MyResult<Void> deleteBatchOrderSetting(@RequestBody List<Long> ids) {
        orderSettingService.removeByIds(ids);
        return MyResult.success();
    }


}
