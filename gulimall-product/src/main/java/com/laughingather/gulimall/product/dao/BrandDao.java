package com.laughingather.gulimall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laughingather.gulimall.product.entity.BrandEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 品牌
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
public interface BrandDao extends BaseMapper<BrandEntity> {

    @Select("SELECT name FROM pms_brand WHERE show_status = 1 AND brand_id = #{brandId}")
    String getNameById(@Param("brandId") Long brandId);
}
