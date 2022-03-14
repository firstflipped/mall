package com.laughingather.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.product.entity.SpuInfoEntity;
import com.laughingather.gulimall.product.entity.param.SpuParam;
import com.laughingather.gulimall.product.entity.query.SpuInfoQuery;
import com.laughingather.gulimall.product.entity.vo.SpuInfoVO;

/**
 * spu逻辑接口
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
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

