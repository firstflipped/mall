package com.laughingather.gulimall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laughingather.gulimall.product.entity.BrandEntity;
import com.laughingather.gulimall.product.entity.vo.BrandSelectVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 品牌管理持久层
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface BrandDao extends BaseMapper<BrandEntity> {

    /**
     * 根据品牌id查询品牌名称
     *
     * @param brandId 品牌id
     * @return 品牌名称
     */
    @Select("SELECT brand_name FROM pms_brand WHERE show_status = 1 AND brand_id = #{brandId}")
    String getBrandNameById(@Param("brandId") Long brandId);

    /**
     * 查询品牌列表（仅供前端下拉选择器使用）
     *
     * @param brandName 品牌名称
     * @return 品牌列表
     */
    List<BrandSelectVO> listBrandsWithSelect(@Param("brandName") String brandName);
}
