package com.laughingather.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.product.entity.CategoryEntity;
import com.laughingather.gulimall.product.entity.vo.Category2VO;
import com.laughingather.gulimall.product.entity.vo.CategoryTreeVO;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
public interface CategoryService extends IService<CategoryEntity> {

    /**
     * 查询所有分类，并将其转换成树形结构
     * @return
     */
    List<CategoryTreeVO> listWithTree();

    /**
     * 批量删除分类
     *
     * @param catIdList
     * @return
     */
    boolean deleteCategoryByIds(List<Long> catIdList);

    /**
     * 根据子节点查询完整节点关系
     *
     * @param categoryId
     * @return
     */
    Long[] getCategoryPath(@NotNull Long categoryId);

    /**
     * 查询所有一级分类
     *
     * @return
     */
    List<CategoryEntity> listLevel1Category();


    Map<String, List<Category2VO>> getCategoryJSONFromDb();

    Map<String, List<Category2VO>> getCategoryJSON();
}

