package com.laughingather.gulimall.order.feign.service;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.order.entity.dto.WareSkuLockDTO;
import com.laughingather.gulimall.order.feign.entity.FareDTO;
import com.laughingather.gulimall.order.feign.entity.SkuHashStockDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 库存服务远程调用类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@FeignClient("gulimall-ware")
@RequestMapping("/gulimall-ware/openapi/ware")
public interface WareFeignService {

    /**
     * 调用库存服务查询商品对应库存
     *
     * @param skuIds 商品id集合
     * @return 商品对应库存列表
     */
    @PostMapping("/stock")
    MyResult<List<SkuHashStockDTO>> getSkusHasStock(@RequestBody List<Long> skuIds);

    /**
     * 获取收货地址信息及运费信息
     *
     * @param addressId 地址id
     * @return 收货地址信息及运费信息
     */
    @GetMapping("/fare")
    MyResult<FareDTO> getFare(@RequestParam("aid") Long addressId);

    /**
     * 订单锁定库存
     *
     * @param wareSkuLockDTO 商品库存信息
     * @return Void
     */
    @PostMapping("/lock/order")
    MyResult<Void> orderLockStock(@RequestBody WareSkuLockDTO wareSkuLockDTO);

}
