package com.laughingather.gulimall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laughingather.gulimall.product.entity.CategoryEntity;
import com.laughingather.gulimall.product.entity.vo.CategoryTreeVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 商品三级分类
 * 
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
public interface CategoryDao extends BaseMapper<CategoryEntity> {

    /**
     * 查询分类列表数据
     *
     * @return 分类列表
     */
    List<CategoryTreeVO> selectListWithTree();

    /**
     * 根据分类id查询分类名称
     *
     * @param categoryId 分类id
     * @return 分类名称
     */
    @Select("SELECT category_name FROM pms_category WHERE show_status = 1 AND category_id = #{categoryId}")
    String getCategoryNameById(@Param("categoryId") Long categoryId);
}
