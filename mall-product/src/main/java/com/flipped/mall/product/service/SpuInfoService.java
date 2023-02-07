package com.flipped.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.product.entity.param.SpuParam;
import com.flipped.mall.product.entity.SpuInfoEntity;
import com.flipped.mall.product.entity.query.SpuInfoQuery;
import com.flipped.mall.product.entity.vo.SpuInfoVO;

/**
 * spu逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    /**
     * 保存spu信息
     *
     * @param spuParam 前端传入spu信息
     */
    void saveSpuInfo(SpuParam spuParam);

    /**
     * 分页查询spu信息
     *
     * @param spuInfoQuery spu列表查询条件
     * @return spuVO列表
     */
    MyPage<SpuInfoVO> listSpuWithPage(SpuInfoQuery spuInfoQuery);

    /**
     * 上架
     *
     * @param spuId spuId
     */
    void upSpu(Long spuId);

    /**
     * 获取spu信息
     *
     * @param skuId skuId
     * @return spu详情信息
     */
    SpuInfoVO getSpuInfoBySkuId(Long skuId);
}

