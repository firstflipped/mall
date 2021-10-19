package com.laughingather.gulimall.order.feign;

import com.laughingather.gulimall.order.entity.vo.SkuHashStockVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @PostMapping("/hasStock")
    List<SkuHashStockVO> getSkusHasStock(@RequestBody List<Long> skuIds);

}
