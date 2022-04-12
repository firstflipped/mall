package com.laughingather.gulimall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laughingather.gulimall.product.entity.AttrGroupEntity;
import com.laughingather.gulimall.product.entity.vo.SpuItemAttrGroupWithAttrVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 属性分组
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface AttrGroupDao extends BaseMapper<AttrGroupEntity> {

    /**
     * 根据属性id获取属性分组名称
     *
     * @param attrId 属性id
     * @return 属性分组名称
     */
    String getGroupNameByAttrId(@Param("attrId") Long attrId);

    /**
     * 查询属性分组及其关联属性
     *
     * @param categoryId 分类id
     * @param spuId      spuId
     * @return 属性分组列表
     */
    List<SpuItemAttrGroupWithAttrVO> getAttrGroupWithAttrsBySpuId(@Param("categoryId") Long categoryId,
                                                                  @Param("spuId") Long spuId);
}
