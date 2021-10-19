package com.laughingather.gulimall.product.openapi;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.product.entity.SkuInfoEntity;
import com.laughingather.gulimall.product.service.SkuInfoService;
import com.laughingather.gulimall.product.service.SkuSaleAttrValueService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

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
    @Resource
    private SkuSaleAttrValueService skuSaleAttrValueService;

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
     * 根据skuId查询商品价格
     *
     * @param skuId
     * @return
     */
    @GetMapping("/{sku-id}/sku-price")
    BigDecimal getSkuPriceBySkuId(@PathVariable("sku-id") Long skuId) {
        SkuInfoEntity byId = skuInfoService.getById(skuId);
        if (byId != null) {
            return byId.getPrice();
        }
        return null;
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


    /**
     * 获取销售属性信息
     *
     * @param skuId
     * @return
     */
    @GetMapping("/sku-sale-attr-value/list/{skuId}")
    public MyResult<List<String>> getSkuSaleAttrValues(@PathVariable("skuId") Long skuId) {
        List<String> skuSaleAttrValues = skuSaleAttrValueService.getSkuSaleAttrValuesAsString(skuId);
        return CollectionUtils.isNotEmpty(skuSaleAttrValues) ? MyResult.success(skuSaleAttrValues) :
                MyResult.failed();
    }

}