package com.flipped.mall.product.feign.service;

import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.product.feign.entity.SkuHasStockDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 库存服务第三方调用接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@FeignClient("mall-ware")
public interface WareFeignService {

    /**
     * 查询sku的库存信息
     *
     * @param skuIds skuId集合
     * @return sku库存信息传输实体集合
     */
    @PostMapping("/mall-ware/openapi/ware/stock")
    MyResult<List<SkuHasStockDTO>> getSkusHasStock(@RequestBody List<Long> skuIds);
}
