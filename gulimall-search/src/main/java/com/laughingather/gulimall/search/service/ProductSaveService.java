package com.laughingather.gulimall.search.service;


import com.laughingather.gulimall.search.entity.EsSku;

import java.io.IOException;
import java.util.List;

public interface ProductSaveService {

    /**
     * 商品上架
     *
     * @param esSkus 商品集合
     */
    boolean productUp(List<EsSku> esSkus) throws IOException;
}
