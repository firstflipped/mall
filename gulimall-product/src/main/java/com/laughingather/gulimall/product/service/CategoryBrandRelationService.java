package com.laughingather.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.product.entity.CategoryBrandRelationEntity;
import com.laughingather.gulimall.product.entity.vo.CategoryBrandRelationVO;

import java.util.List;

/**
 * 品牌分类关联
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    List<CategoryBrandRelationVO> listCategoryByBrandId(Long brandId);

    void saveCategoryBrandRelation(CategoryBrandRelationEntity categoryBrandRelation);
}

