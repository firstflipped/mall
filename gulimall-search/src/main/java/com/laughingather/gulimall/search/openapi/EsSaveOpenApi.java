package com.laughingather.gulimall.search.openapi;

import com.laughingather.gulimall.common.api.ErrorCodeEnum;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.search.entity.SkuESModel;
import com.laughingather.gulimall.search.service.ProductSaveService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author WangJie
 */
@RestController
@RequestMapping("/openapi/search")
public class EsSaveOpenApi {

    @Resource
    private ProductSaveService productSaveService;

    /**
     * 商品上架
     *
     * @param skuESModels
     * @return
     */
    @PostMapping("/product")
    public MyResult productStatusUp(@RequestBody List<SkuESModel> skuESModels) {
        try {
            boolean isSuccess = productSaveService.productStatusUp(skuESModels);
            return !isSuccess ? MyResult.success() :
                    MyResult.builder().code(ErrorCodeEnum.PRODUCT_UP_EXCEPTION.getCode())
                            .message(ErrorCodeEnum.PRODUCT_UP_EXCEPTION.getMessage()).build();
        } catch (IOException e) {
            e.printStackTrace();
            return MyResult.builder().code(ErrorCodeEnum.PRODUCT_UP_EXCEPTION.getCode())
                    .message(ErrorCodeEnum.PRODUCT_UP_EXCEPTION.getMessage()).build();
        }
    }

}
