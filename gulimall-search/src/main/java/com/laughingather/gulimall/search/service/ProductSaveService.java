package com.laughingather.gulimall.search.service;


import com.laughingather.gulimall.search.entity.EsSku;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface ProductSaveService {

    /**
     * 商品上架
     *
     * @param esSkus 商品集合
     * @return 是否上架成功
     * @throws IOException
     */
    boolean productUp(List<EsSku> esSkus) throws IOException;
}
