package com.laughingather.gulimall.product.feign.service;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.product.entity.to.SkuEsTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("gulimall-search")
public interface SearchFeignService {

    @PostMapping("/gulimall-search/openapi/search/product")
    MyResult productStatusUp(@RequestBody List<SkuEsTO> skuEsTOs);

}
