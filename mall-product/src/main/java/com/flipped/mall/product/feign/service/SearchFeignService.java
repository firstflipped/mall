package com.flipped.mall.product.feign.service;

import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.product.entity.dto.SkuEsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


/**
 * 检索服务第三方调用接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@FeignClient("mall-search")
public interface SearchFeignService {

    /**
     * 商品上架，保存至ES
     *
     * @param skuEsDTOList skuEs传输实体集合
     */
    @PostMapping("/mall-search/openapi/search/product")
    MyResult<Void> productStatusUp(@RequestBody List<SkuEsDTO> skuEsDTOList);

}
