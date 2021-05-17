package com.laughingather.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.product.dao.BrandDao;
import com.laughingather.gulimall.product.dao.CategoryBrandRelationDao;
import com.laughingather.gulimall.product.dao.CategoryDao;
import com.laughingather.gulimall.product.entity.CategoryBrandRelationEntity;
import com.laughingather.gulimall.product.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

    @Resource
    private CategoryBrandRelationDao categoryBrandRelationDao;
    @Autowired
    private BrandDao brandDao;
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<CategoryBrandRelationEntity> listCategoryByBrandId(Long brandId) {
        List<CategoryBrandRelationEntity> categoryBrandRelationList = categoryBrandRelationDao.selectList(new QueryWrapper<CategoryBrandRelationEntity>()
                .lambda().eq(CategoryBrandRelationEntity::getBrandId, brandId));
        return categoryBrandRelationList;
    }

    @Override
    public List<CategoryBrandRelationEntity> listBrandsByCategoryId(Long categoryId) {
        List<CategoryBrandRelationEntity> categoryBrandRelations = categoryBrandRelationDao.selectList(new QueryWrapper<CategoryBrandRelationEntity>()
                .lambda().eq(CategoryBrandRelationEntity::getCatalogId, categoryId));
        return categoryBrandRelations;
    }

    @Override
    public void saveCategoryBrandRelation(CategoryBrandRelationEntity categoryBrandRelation) {
        Long brandId = categoryBrandRelation.getBrandId();
        Long catalogId = categoryBrandRelation.getCatalogId();

        // 查询品牌名称和分类名称
        String brandName = brandDao.getNameById(brandId);
        String categoryName = categoryDao.getNameById(catalogId);
        categoryBrandRelation.setBrandName(brandName);
        categoryBrandRelation.setCatalogName(categoryName);

        categoryBrandRelationDao.insert(categoryBrandRelation);
    }
}