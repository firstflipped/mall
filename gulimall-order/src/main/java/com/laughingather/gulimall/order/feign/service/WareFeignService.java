package com.laughingather.gulimall.order.feign.service;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.order.entity.dto.WareSkuLockDTO;
import com.laughingather.gulimall.order.feign.entity.FareTO;
import com.laughingather.gulimall.order.feign.entity.SkuHashStockTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 库存服务远程调用类
 *
 * @author：laughingather
 * @create：2021-10-18 2021/10/18
 */
@FeignClient("gulimall-ware")
@RequestMapping("/gulimall-ware/openapi/ware")
public interface WareFeignService {

    /**
     * 调用库存服务查询商品对应库存
     *
     * @param skuIds
     * @return
     */
    @PostMapping("/stock")
    MyResult<List<SkuHashStockTO>> getSkusHasStock(@RequestBody List<Long> skuIds);

    /**
     * 获取收货地址信息及运费信息
     *
     * @param addressId
     * @return
     */
    @GetMapping("/fare")
    MyResult<FareTO> getFare(@RequestParam("aid") Long addressId);

    /**
     * 订单锁定库存
     *
     * @param wareSkuLockDTO
     * @return
     */
    @PostMapping("/lock/order")
    MyResult<Void> orderLockStock(@RequestBody WareSkuLockDTO wareSkuLockDTO);

}
