package com.laughingather.gulimall.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.product.dao.BrandDao;
import com.laughingather.gulimall.product.dao.CategoryBrandRelationDao;
import com.laughingather.gulimall.product.dao.CategoryDao;
import com.laughingather.gulimall.product.entity.CategoryBrandRelationEntity;
import com.laughingather.gulimall.product.entity.vo.CategoryBrandRelationVO;
import com.laughingather.gulimall.product.service.CategoryBrandRelationService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
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
    public List<CategoryBrandRelationVO> listCategoryByBrandId(Long brandId) {
        List<CategoryBrandRelationVO> categoryBrandRelationVOList = categoryBrandRelationDao.listCategoryByBrandId(brandId);
        return CollectionUtils.isNotEmpty(categoryBrandRelationVOList) ?
                categoryBrandRelationVOList : Collections.emptyList();
    }

    @Override
    public void saveCategoryBrandRelation(CategoryBrandRelationEntity categoryBrandRelation) {
        Long brandId = categoryBrandRelation.getBrandId();
        Long catelogId = categoryBrandRelation.getCatelogId();

        // 查询品牌名称和分类名称
        String brandName = brandDao.getNameById(brandId);
        String categoryName = categoryDao.getNameById(catelogId);
        categoryBrandRelation.setBrandName(brandName);
        categoryBrandRelation.setCatelogName(categoryName);

        categoryBrandRelationDao.insert(categoryBrandRelation);
    }
}