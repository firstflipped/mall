package com.laughingather.gulimall.search.service;


import com.laughingather.gulimall.search.entity.SkuESModel;

import java.io.IOException;
import java.util.List;

public interface ProductSaveService {

    /**
     * 商品上架
     *
     * @param skuESModels
     */
    boolean productStatusUp(List<SkuESModel> skuESModels) throws IOException;
}
