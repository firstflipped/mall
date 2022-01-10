package com.laughingather.gulimall.search.openapi;

import com.laughingather.gulimall.common.api.ErrorCodeEnum;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.search.entity.EsSku;
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
    public MyResult productUp(@RequestBody List<EsSku> skuESModels) {
        try {
            // 保存到es是否发生了错误
            boolean hasFailures = productSaveService.productUp(skuESModels);
            return !hasFailures ? MyResult.success() :
                    MyResult.failed(ErrorCodeEnum.PRODUCT_UP_EXCEPTION);
        } catch (IOException e) {
            e.printStackTrace();
            return MyResult.failed(ErrorCodeEnum.PRODUCT_UP_EXCEPTION);
        }
    }

}
