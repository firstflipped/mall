package com.laughingather.gulimall.ware.openapi;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.ware.entity.vo.SkuHasStockVO;
import com.laughingather.gulimall.ware.service.WareInfoService;
import com.laughingather.gulimall.ware.service.WareSkuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


/**
 * 库存服务开放接口
 *
 * @author laughingather
 */
@Slf4j
@RestController
@RequestMapping("/openapi/ware")
public class WareOpenApi {

    @Autowired
    private WareSkuService wareSkuService;
    @Autowired
    private WareInfoService wareInfoService;

    @PostMapping("/hasStock")
    public List<SkuHasStockVO> getSkusHasStock(@RequestBody List<Long> skuIds) {
        List<SkuHasStockVO> skuHasStockVOS = wareSkuService.getSkusHasStock(skuIds);
        return skuHasStockVOS;
    }


    @GetMapping("/fare")
    public MyResult<BigDecimal> getFare(@RequestParam("aid") Long addressId) {
        BigDecimal fare = wareInfoService.getFare(addressId);
        return MyResult.success(fare);
    }

}