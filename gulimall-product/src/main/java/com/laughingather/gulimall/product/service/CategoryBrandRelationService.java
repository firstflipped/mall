package com.laughingather.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.product.entity.CategoryBrandRelationEntity;

import java.util.List;

/**
 * 品牌分类关联
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    List<CategoryBrandRelationEntity> listCategoryByBrandId(Long brandId);

    List<CategoryBrandRelationEntity> listBrandsByCategoryId(Long categoryId);

    void saveCategoryBrandRelation(CategoryBrandRelationEntity categoryBrandRelation);
}

