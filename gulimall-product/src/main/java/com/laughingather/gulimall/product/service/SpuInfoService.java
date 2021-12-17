package com.laughingather.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.product.entity.SpuInfoEntity;
import com.laughingather.gulimall.product.entity.param.SpuParam;
import com.laughingather.gulimall.product.entity.query.SpuInfoQuery;
import com.laughingather.gulimall.product.entity.vo.SpuInfoVO;

/**
 * spu信息
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    /**
     * 保存spu信息
     *
     * @param spuParam
     */
    void saveSpuInfo(SpuParam spuParam);

    /**
     * 分页查询spu信息
     *
     * @param spuInfoQuery
     * @return
     */
    MyPage<SpuInfoEntity> listSpuWithPage(SpuInfoQuery spuInfoQuery);

    /**
     * 上架
     *
     * @param spuId
     */
    void upSpu(Long spuId);

    /**
     * 获取spu信息
     *
     * @param skuId
     * @return
     */
    SpuInfoVO getSpuInfoBySkuId(Long skuId);
}

