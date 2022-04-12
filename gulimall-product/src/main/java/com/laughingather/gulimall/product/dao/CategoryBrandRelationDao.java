package com.laughingather.gulimall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laughingather.gulimall.product.entity.BrandEntity;
import com.laughingather.gulimall.product.entity.CategoryBrandRelationEntity;
import com.laughingather.gulimall.product.entity.CategoryEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 品牌分类关联
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface CategoryBrandRelationDao extends BaseMapper<CategoryBrandRelationEntity> {

    /**
     * 查询分类列表
     *
     * @param brandId 品牌id
     * @return
     */
    List<CategoryEntity> listCategoryByBrandId(@Param("brandId") Long brandId);

    /**
     * 查询品牌列表
     *
     * @param categoryId 分类id
     * @return
     */
    List<BrandEntity> listBrandsByCategoryId(@Param("categoryId") Long categoryId);
}
