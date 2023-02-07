package com.flipped.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.product.entity.SkuInfoEntity;
import com.flipped.mall.product.entity.query.SkuInfoQuery;
import com.flipped.mall.product.entity.vo.SkuItemVO;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * sku逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    /**
     * 条件分页查询sku信息
     *
     * @param skuInfoQuery sku列表查询条件
     * @return sku列表
     */
    MyPage<SkuInfoEntity> listSkusWithPage(SkuInfoQuery skuInfoQuery);

    /**
     * 根据spuId获取sku列表
     *
     * @param spuId spuId
     * @return sku列表
     */
    List<SkuInfoEntity> listSkusBySpuId(Long spuId);

    /**
     * 根据skuId获取sku详情
     *
     * @param skuId skuId
     * @return skuVO详情信息
     * @throws ExecutionException   异常
     * @throws InterruptedException 异常
     */
    SkuItemVO getSkuItemById(Long skuId) throws ExecutionException, InterruptedException;
}

