package com.laughingather.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.product.entity.BrandEntity;
import com.laughingather.gulimall.product.entity.CategoryBrandRelationEntity;
import com.laughingather.gulimall.product.entity.CategoryEntity;

import java.util.List;

/**
 * 品牌分类关联关系管理逻辑接口
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    /**
     * 查询分类列表
     *
     * @param brandId 品牌id
     * @return 分类列表
     */
    List<CategoryEntity> listCategoryByBrandId(Long brandId);

    /**
     * 查询品牌列表
     *
     * @param categoryId 分类id
     * @return 品牌列表
     */
    List<BrandEntity> listBrandsByCategoryId(Long categoryId);

    /**
     * 保存品牌&分类关联关系
     *
     * @param categoryBrandRelation 品牌分类关联关系
     */
    void saveCategoryBrandRelation(CategoryBrandRelationEntity categoryBrandRelation);
}

