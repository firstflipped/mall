package com.flipped.mall.order.controller;

import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.common.entity.query.PageQuery;
import com.flipped.mall.order.entity.OrderSettingEntity;
import com.flipped.mall.order.service.OrderSettingService;
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
public class OrderSettingController {

    @Resource
    private OrderSettingService orderSettingService;


    @GetMapping("/page")
    public MyResult<MyPage<OrderSettingEntity>> listOrderSettingsWithPage(@ModelAttribute PageQuery pageQuery) {
        MyPage<OrderSettingEntity> orderSettingPage = orderSettingService.listOrderSettingsWithPage(pageQuery);
        return MyResult.success(orderSettingPage);
    }


    @GetMapping("/{sid}")
    public MyResult<OrderSettingEntity> getOrderSetting(@PathVariable("sid") Long id) {
        OrderSettingEntity orderSetting = orderSettingService.getById(id);
        return MyResult.success(orderSetting);
    }


    @PostMapping
    public MyResult saveOrderSetting(@RequestBody OrderSettingEntity orderSetting) {
        orderSettingService.saveOrderSetting(orderSetting);
        return MyResult.success();
    }


    @PutMapping
    public MyResult<Void> updateOrderSetting(@RequestBody OrderSettingEntity orderSetting) {
        orderSettingService.updateOrderSetting(orderSetting);
        return MyResult.success();
    }


    @DeleteMapping
    public MyResult<Void> deleteBatchOrderSetting(@RequestBody List<Long> ids) {
        orderSettingService.removeByIds(ids);
        return MyResult.success();
    }

}
