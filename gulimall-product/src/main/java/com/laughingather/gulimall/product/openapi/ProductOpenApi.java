package com.laughingather.gulimall.product.openapi;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.product.entity.SkuInfoEntity;
import com.laughingather.gulimall.product.service.SkuInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 商品服务开放接口
 *
 * @author laughingather
 */
@Slf4j
@RestController
@RequestMapping("/openapi/product")
public class ProductOpenApi {

    @Resource
    private SkuInfoService skuInfoService;

    /**
     * 根据skuId获取商品名称
     *
     * @param skuId
     * @return
     */
    @GetMapping("/skuInfo/getSkuName")
    public String getSkuNameBySkuId(@RequestParam("skuId") Long skuId) {
        SkuInfoEntity byId = skuInfoService.getById(skuId);
        if (byId != null) {
            return byId.getSkuName();
        }
        return "";
    }


    /**
     * 根据商品id获取商品详情
     *
     * @param skuId
     * @return
     */
    @GetMapping("/{skuId}/info")
    public MyResult<SkuInfoEntity> getSkuInfoBySkuId(@PathVariable("skuId") Long skuId) {
        SkuInfoEntity byId = skuInfoService.getById(skuId);

        return MyResult.success(byId);
    }


}