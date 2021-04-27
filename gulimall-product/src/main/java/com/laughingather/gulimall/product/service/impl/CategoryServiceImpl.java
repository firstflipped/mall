package com.laughingather.gulimall.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.constant.ProductConstants;
import com.laughingather.gulimall.product.dao.CategoryDao;
import com.laughingather.gulimall.product.entity.CategoryEntity;
import com.laughingather.gulimall.product.entity.vo.CategoryTreeVO;
import com.laughingather.gulimall.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author WangJie
 */
@Slf4j
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Resource
    private CategoryDao categoryDao;

    @Override
    public List<CategoryTreeVO> listWithTree() {
        // 查询所有分类
        List<CategoryTreeVO> categoryList = categoryDao.selectListWithTree();

        List<CategoryTreeVO> level1Menus = categoryList.stream()
                .filter(category -> ProductConstants.categoryRootId.equals(category.getParentCid()))
                .map(category -> {
                    category.setChildren(getChildless(category, categoryList));
                    return category;
                })
                .sorted(Comparator.comparingInt(CategoryTreeVO::getSort))
                .collect(Collectors.toList());

        return level1Menus;
    }

    @Override
    public boolean deleteCategoryByIds(List<Long> catIdList) {
        // TODO:遍历需要被删除的id，确定在其他位置是否存在引用

        int deleteCounts = categoryDao.deleteBatchIds(catIdList);
        return deleteCounts > 0 ? true : false;
    }

    @Override
    public Long[] getCatelogPath(@NotNull Long catelogId) {
        List<Long> catelogPath = new ArrayList<>();

        catelogPath = findParentById(catelogId, catelogPath);
        Collections.reverse(catelogPath);

        return catelogPath.toArray(new Long[catelogPath.size()]);
    }

    private List<Long> findParentById(Long catelogId, List<Long> catelogPath) {
        // 收集当前节点id
        catelogPath.add(catelogId);

        CategoryEntity category = categoryDao.selectById(catelogId);
        Long parentCid = category.getParentCid();
        if (!ProductConstants.categoryRootId.equals(parentCid)) {
            findParentById(parentCid, catelogPath);
        }

        return catelogPath;
    }

    /**
     * 递归查找所有菜单的子菜单
     *
     * @param root
     * @param all
     * @return
     */
    private List<CategoryTreeVO> getChildless(CategoryTreeVO root, List<CategoryTreeVO> all) {
        List<CategoryTreeVO> children = all.stream()
                .filter(category -> category.getParentCid().equals(root.getCatId()))
                // 找到子菜单
                .map(category -> {
                    category.setChildren(getChildless(category, all));
                    return category;
                })
                .sorted((c1, c2) -> (c1.getSort() == null ? 0 : c1.getSort()) - (c2.getSort() == null ? 0 : c1.getSort()))
                .collect(Collectors.toList());

        return children;
    }
}