package com.laughingather.gulimall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laughingather.gulimall.product.entity.CategoryBrandRelationEntity;
import com.laughingather.gulimall.product.entity.vo.CategoryBrandRelationVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 品牌分类关联
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
public interface CategoryBrandRelationDao extends BaseMapper<CategoryBrandRelationEntity> {

    List<CategoryBrandRelationVO> listCategoryByBrandId(@Param("brandId") Long brandId);
}
