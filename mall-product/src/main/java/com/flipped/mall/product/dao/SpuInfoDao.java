package com.flipped.mall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.flipped.mall.product.entity.SpuInfoEntity;
import com.flipped.mall.product.entity.query.SpuInfoQuery;
import com.flipped.mall.product.entity.vo.SpuInfoVO;
import org.apache.ibatis.annotations.Param;

/**
 * spu信息
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface SpuInfoDao extends BaseMapper<SpuInfoEntity> {

    /**
     * 分页查询spu列表
     *
     * @param page
     * @param spuInfoQuery
     * @return
     */
    IPage<SpuInfoEntity> listSpuWithPage(IPage<SpuInfoEntity> page, @Param("params") SpuInfoQuery spuInfoQuery);

    /**
     * 更新spu状态
     *
     * @param spuId
     * @param status
     */
    void updateSpuStatus(@Param("spuId") Long spuId, @Param("status") Integer status);

    /**
     * 获取spu信息
     *
     * @param skuId
     * @return
     */
    SpuInfoVO getSpuInfoBySkuId(@Param("skuId") Long skuId);
}
