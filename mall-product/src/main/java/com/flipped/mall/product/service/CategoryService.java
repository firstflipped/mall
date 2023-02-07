package com.flipped.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flipped.mall.product.entity.CategoryEntity;
import com.flipped.mall.product.entity.vo.Category2VO;
import com.flipped.mall.product.entity.vo.CategoryTreeVO;

import java.util.List;
import java.util.Map;

/**
 * 分类管理逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface CategoryService extends IService<CategoryEntity> {

    /**
     * 查询所有分类，并将其转换成树形结构
     *
     * @return 分类树形接口列表
     */
    List<CategoryTreeVO> listWithTree();

    /**
     * 批量删除分类
     *
     * @param categoryIds 分类id集合
     */
    void deleteCategoryByIds(List<Long> categoryIds);

    /**
     * 根据子节点查询完整节点关系
     *
     * @param categoryId 分类id
     * @return 完整分类节点关系
     */
    Long[] getCategoryPath(Long categoryId);

    /**
     * 查询所有一级分类
     *
     * @return 一级分类列表
     */
    List<CategoryEntity> listLevel1Category();


    /**
     * 查询分类列表并以属性接口展示（不使用缓存版本）
     *
     * @return 分类字典数据
     */
    Map<String, List<Category2VO>> getCategoryMapFromDb();

    /**
     * 查询分类列表并以属性接口展示（使用缓存版本）
     *
     * @return 分类字典数据
     */
    Map<String, List<Category2VO>> getCategoryMap();

    /**
     * 根据分类id查询分类名称
     *
     * @param categoryId 分类id
     * @return 分类名称
     */
    String getCategoryNameByCategoryId(Long categoryId);
}

