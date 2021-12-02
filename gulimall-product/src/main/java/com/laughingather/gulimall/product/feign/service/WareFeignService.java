package com.laughingather.gulimall.product.feign.service;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.product.feign.entity.SkuHasStockVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 库存服务第三方调用接口
 *
 * @author laughingather
 */
@FeignClient("gulimall-ware")
public interface WareFeignService {

    /**
     * 查询sku的库存信息
     *
     * @param skuIds
     * @return
     */
    @PostMapping("/gulimall-ware/openapi/ware/hasStock")
    MyResult<List<SkuHasStockVO>> getSkusHasStock(@RequestBody List<Long> skuIds);
}
