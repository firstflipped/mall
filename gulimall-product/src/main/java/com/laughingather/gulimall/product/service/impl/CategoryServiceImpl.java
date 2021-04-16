package com.laughingather.gulimall.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.constant.Constants;
import com.laughingather.gulimall.product.dao.CategoryDao;
import com.laughingather.gulimall.product.entity.CategoryEntity;
import com.laughingather.gulimall.product.entity.vo.CategoryTreeVO;
import com.laughingather.gulimall.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author WangJie
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<CategoryTreeVO> listWithTree() {
        // 查询所有分类
        List<CategoryTreeVO> categoryList = categoryDao.selectListWithTree();

        List<CategoryTreeVO> level1Menus = categoryList.stream()
                .filter(category -> category.getParentCid() == Constants.categoryRootId)
                .map(category -> {
                    category.setChildren(getChildrens(category, categoryList));
                    return category;
                })
                .sorted(Comparator.comparingInt(CategoryTreeVO::getSort))
                .collect(Collectors.toList());

        return level1Menus;
    }

    /**
     * 递归查找所有菜单的子菜单
     * @param root
     * @param all
     * @return
     */
    private List<CategoryTreeVO> getChildrens(CategoryTreeVO root, List<CategoryTreeVO> all) {
        List<CategoryTreeVO> children = all.stream()
                .filter(category -> category.getParentCid() == root.getCatId())
                // 找到子菜单
                .map(category -> {
                    category.setChildren(getChildrens(category, all));
                    return category;
                })
                .sorted(Comparator.comparingInt(CategoryTreeVO::getSort))
                .collect(Collectors.toList());

        return children;
    }
}