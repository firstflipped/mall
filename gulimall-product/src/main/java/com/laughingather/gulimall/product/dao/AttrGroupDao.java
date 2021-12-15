package com.laughingather.gulimall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laughingather.gulimall.product.entity.AttrGroupEntity;
import com.laughingather.gulimall.product.entity.vo.AttrGroupVO;
import com.laughingather.gulimall.product.entity.vo.SpuItemGroupAttrVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 属性分组
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
public interface AttrGroupDao extends BaseMapper<AttrGroupEntity> {

    /**
     * 查询属性分组详情
     *
     * @param attrGroupId
     * @return
     */
    AttrGroupVO getAttrGroupById(@Param("attrGroupId") Long attrGroupId);

    /**
     * 根据属性id获取属性分组名称
     *
     * @param attrId
     * @return
     */
    String getGroupNameByAttrId(@Param("attrId") Long attrId);

    /**
     * 查询属性分组及其关联属性
     *
     * @param categoryId 分类id
     * @param spuId
     * @return
     */
    List<SpuItemGroupAttrVO> getAttrGroupWithAttrsBySpuId(@Param("categoryId") Long categoryId,
                                                          @Param("spuId") Long spuId);
}
