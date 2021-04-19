package com.laughingather.gulimall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laughingather.gulimall.product.entity.CategoryEntity;
import com.laughingather.gulimall.product.entity.vo.CategoryTreeVO;

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
     * 查询列表数据
     * @return
     */
    List<CategoryTreeVO> selectListWithTree();
}
