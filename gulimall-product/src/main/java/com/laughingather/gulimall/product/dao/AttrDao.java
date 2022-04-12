package com.laughingather.gulimall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laughingather.gulimall.product.entity.AttrEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品属性
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface AttrDao extends BaseMapper<AttrEntity> {

    /**
     * 查询可以快速检索的属性id集合
     *
     * @param attrIds 属性id集合
     * @return 属性id集合
     */
    List<Long> selectSearchAttrIds(@Param("attrIds") List<Long> attrIds);
}
