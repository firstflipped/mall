package com.laughingather.gulimall.product.dao;

import com.laughingather.gulimall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
