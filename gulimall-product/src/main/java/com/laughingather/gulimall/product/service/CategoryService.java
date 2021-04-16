package com.laughingather.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
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
public interface CategoryService extends IService<CategoryEntity> {

    /**
     * 查询所有分类，并将其转换成树形结构
     * @return
     */
    List<CategoryTreeVO> listWithTree();
}

