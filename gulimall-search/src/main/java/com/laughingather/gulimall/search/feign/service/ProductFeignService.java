package com.laughingather.gulimall.search.feign.service;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.search.feign.entity.AttrTO;
import com.laughingather.gulimall.search.feign.entity.BrandTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 商品服务远程调用类
 *
 * @author：laughingather
 * @create：2021-06-01 21:29
 */
@FeignClient("gulimall-product")
public interface ProductFeignService {


    /**
     * 获取属性信息
     *
     * @param attrId
     * @return
     */
    @GetMapping("/gulimall-product/openapi/product/{aid}")
    MyResult<AttrTO> getAttrById(@PathVariable("aid") Long attrId);

    /**
     * 获取品牌信息
     *
     * @param brandIds
     * @return
     */
    @GetMapping("/gulimall-product/openapi/product/brand/list")
    MyResult<List<BrandTO>> listBrandsByIds(@RequestParam(value = "bids", required = false) List<Long> brandIds);

}
