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

    /**
     * 根据品牌id查询品牌名称
     *
     * @param brandId
     * @return
     */
    @Select("SELECT brand_name FROM pms_brand WHERE show_status = 1 AND brand_id = #{brandId}")
    String getBrandNameById(@Param("brandId") Long brandId);
}
