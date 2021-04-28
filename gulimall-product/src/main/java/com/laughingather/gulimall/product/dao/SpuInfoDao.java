package com.laughingather.gulimall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.laughingather.gulimall.product.entity.SpuInfoEntity;
import com.laughingather.gulimall.product.entity.query.SpuInfoQuery;
import org.apache.ibatis.annotations.Param;

/**
 * spu信息
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
public interface SpuInfoDao extends BaseMapper<SpuInfoEntity> {

    IPage<SpuInfoEntity> pageSpuInfoByParams(IPage<SpuInfoEntity> page, @Param("params") SpuInfoQuery spuInfoQuery);
}
