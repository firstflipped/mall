package com.laughingather.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.constant.ProductConstants;
import com.laughingather.gulimall.product.dao.CategoryDao;
import com.laughingather.gulimall.product.entity.CategoryEntity;
import com.laughingather.gulimall.product.entity.vo.Category2VO;
import com.laughingather.gulimall.product.entity.vo.CategoryTreeVO;
import com.laughingather.gulimall.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.*;
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

    /**
     * 查询所有一级分类
     *
     * @return
     */
    @Override
    public List<CategoryEntity> listLevel1Categorys() {
        return categoryDao.selectList(new LambdaQueryWrapper<CategoryEntity>()
                .eq(CategoryEntity::getCatLevel, 1));
    }

    @Override
    public Map<String, List<Category2VO>> getCatelogJSON() {
        // 1、获取所有一级分类
        List<CategoryEntity> categoryList = listLevel1Categorys();

        // 把一级分类id当键， 二级分类及子类当值
        Map<String, List<Category2VO>> catelogJSON = categoryList.stream().collect(Collectors.toMap(
                k -> k.getCatId().toString(),
                v -> {
                    List<CategoryEntity> category2List = listCategorsByParentId(v.getCatId());
                    List<Category2VO> category2VOList = Collections.emptyList();
                    if (CollectionUtils.isNotEmpty(category2List)) {
                        category2VOList = category2List.stream().map(categorg2 -> {
                            Category2VO category2VO = Category2VO.builder().id(categorg2.getCatId().toString())
                                    .name(categorg2.getName())
                                    .catelog1Id(v.getCatId().toString()).build();

                            List<CategoryEntity> category3List = listCategorsByParentId(categorg2.getCatId());
                            if (CollectionUtils.isNotEmpty(category3List)) {
                                List<Category2VO.Category3VO> category3VOList = category3List.stream().map(categorg3 ->
                                        Category2VO.Category3VO.builder().id(categorg3.getCatId().toString())
                                                .name(categorg3.getName())
                                                .catelog2Id(categorg2.getCatId().toString())
                                                .build()
                                ).collect(Collectors.toList());
                                category2VO.setCatelog3List(category3VOList);
                            }
                            return category2VO;
                        }).collect(Collectors.toList());
                    }
                    return category2VOList;
                }
        ));

        return catelogJSON;
    }


    @Override
    public Map<String, List<Category2VO>> getCatelogJSONNew() {
        // 先查询所有
        List<CategoryEntity> categorys = categoryDao.selectList(null);

        // 1、获取所有一级分类
        List<CategoryEntity> categoryList = listCategorsByParentId(categorys, 0L);

        // 把一级分类id当键， 二级分类及子类当值
        Map<String, List<Category2VO>> catelogJSON = categoryList.stream().collect(Collectors.toMap(
                k -> k.getCatId().toString(),
                v -> {
                    List<CategoryEntity> category2List = listCategorsByParentId(categorys, v.getCatId());
                    List<Category2VO> category2VOList = Collections.emptyList();
                    if (CollectionUtils.isNotEmpty(category2List)) {
                        category2VOList = category2List.stream().map(categorg2 -> {
                            Category2VO category2VO = Category2VO.builder().id(categorg2.getCatId().toString())
                                    .name(categorg2.getName())
                                    .catelog1Id(v.getCatId().toString()).build();

                            List<CategoryEntity> category3List = listCategorsByParentId(categorys, categorg2.getCatId());
                            if (CollectionUtils.isNotEmpty(category3List)) {
                                List<Category2VO.Category3VO> category3VOList = category3List.stream().map(categorg3 ->
                                        Category2VO.Category3VO.builder().id(categorg3.getCatId().toString())
                                                .name(categorg3.getName())
                                                .catelog2Id(categorg2.getCatId().toString())
                                                .build()
                                ).collect(Collectors.toList());
                                category2VO.setCatelog3List(category3VOList);
                            }
                            return category2VO;
                        }).collect(Collectors.toList());
                    }
                    return category2VOList;
                }
        ));

        return catelogJSON;
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

    /**
     * 根据父id查询其子分类
     *
     * @param parentId
     * @return
     */
    private List<CategoryEntity> listCategorsByParentId(Long parentId) {
        return categoryDao.selectList(new LambdaQueryWrapper<CategoryEntity>().eq(CategoryEntity::getParentCid, parentId));
    }

    private List<CategoryEntity> listCategorsByParentId(List<CategoryEntity> categoryList, Long parentId) {
        return categoryList.stream().filter(item -> parentId.equals(item.getParentCid())).collect(Collectors.toList());
    }
}