package com.laughingather.gulimall.ware.openapi;

import com.laughingather.gulimall.ware.entity.vo.SkuHasStockVO;
import com.laughingather.gulimall.ware.service.WareSkuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/openapi/ware")
public class WareOpenApi {

    @Autowired
    private WareSkuService wareSkuService;

    @PostMapping("/hasStock")
    public List<SkuHasStockVO> getSkusHasStock(@RequestBody List<Long> skuIds) {
        List<SkuHasStockVO> skuHasStockVOS = wareSkuService.getSkusHasStock(skuIds);
        return skuHasStockVOS;
    }

}