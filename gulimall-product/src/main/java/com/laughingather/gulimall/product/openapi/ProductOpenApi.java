package com.laughingather.gulimall.product.openapi;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.product.entity.BrandEntity;
import com.laughingather.gulimall.product.entity.SkuInfoEntity;
import com.laughingather.gulimall.product.entity.dto.AttrDTO;
import com.laughingather.gulimall.product.entity.vo.AttrVO;
import com.laughingather.gulimall.product.entity.vo.SpuInfoVO;
import com.laughingather.gulimall.product.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 商品服务开放接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Slf4j
@RestController
@RequestMapping("/openapi/product")
public class ProductOpenApi {

    @Resource
    private AttrService attrService;
    @Resource
    private BrandService brandService;
    @Resource
    private SkuInfoService skuInfoService;
    @Resource
    private SpuInfoService spuInfoService;
    @Resource
    private SkuSaleAttrValueService skuSaleAttrValueService;

    /**
     * 根据skuId获取商品名称
     *
     * @param skuId skuId
     * @return sku名称
     */
    @GetMapping("/{sid}/name")
    public MyResult<String> getSkuNameBySkuId(@PathVariable("sid") Long skuId) {
        SkuInfoEntity skuInfo = skuInfoService.getById(skuId);

        return skuInfo != null ? MyResult.success(skuInfo.getSkuName()) : MyResult.failed();
    }


    /**
     * 根据skuId查询商品价格
     *
     * @param skuId skuId
     * @return sku价格
     */
    @GetMapping("/{sid}/price")
    public MyResult<BigDecimal> getSkuPriceBySkuId(@PathVariable("sid") Long skuId) {
        SkuInfoEntity skuInfo = skuInfoService.getById(skuId);

        return skuInfo != null ? MyResult.success(skuInfo.getPrice()) : MyResult.failed();
    }


    /**
     * 根据商品id获取商品详情
     *
     * @param skuId skuId
     * @return sku实体
     */
    @GetMapping("/{sid}/info")
    public MyResult<SkuInfoEntity> getSkuInfoBySkuId(@PathVariable("sid") Long skuId) {
        SkuInfoEntity skuInfo = skuInfoService.getById(skuId);

        return skuInfo != null ? MyResult.success(skuInfo) : MyResult.failed();
    }


    /**
     * 获取销售属性信息
     *
     * @param skuId skuId
     * @return 销售属性集合
     */
    @GetMapping("/sku-sale-attr-value/list/{sid}")
    public MyResult<List<String>> getSkuSaleAttrValues(@PathVariable("sid") Long skuId) {
        List<String> skuSaleAttrValues = skuSaleAttrValueService.getSkuSaleAttrValuesAsString(skuId);
        return CollectionUtils.isNotEmpty(skuSaleAttrValues) ? MyResult.success(skuSaleAttrValues) :
                MyResult.failed();
    }


    /**
     * 获取spu信息
     *
     * @param skuId skuId
     * @return spu实体
     */
    @GetMapping("/spu-info")
    public MyResult<SpuInfoVO> getSpuInfoBySkuId(@RequestParam("sid") Long skuId) {
        SpuInfoVO spuInfo = spuInfoService.getSpuInfoBySkuId(skuId);

        if (spuInfo != null) {
            return MyResult.success(spuInfo);
        }
        return MyResult.failed();
    }


    /**
     * 获取属性信息
     *
     * @param attrId 属性id
     * @return 属性服务传输实体
     */
    @GetMapping("/{aid}")
    public MyResult<AttrDTO> getAttrById(@PathVariable("aid") Long attrId) {
        AttrVO attrVO = attrService.getAttrVOById(attrId);

        AttrDTO attrDTO = new AttrDTO();
        BeanUtils.copyProperties(attrVO, attrDTO);
        return MyResult.success(attrDTO);
    }


    /**
     * 获取品牌列表
     *
     * @param brandIds 品牌id集合
     * @return 品牌实体集合
     */
    @GetMapping("/brand/list")
    public MyResult<List<BrandEntity>> listBrandsByIds(@RequestParam(value = "bids", required = false) List<Long> brandIds) {
        List<BrandEntity> brands;
        if (CollectionUtils.isNotEmpty(brandIds)) {
            brands = brandService.listByIds(brandIds);
        } else {
            brands = brandService.list();
        }

        return CollectionUtils.isNotEmpty(brands) ? MyResult.success(brands) : MyResult.failed();
    }

}