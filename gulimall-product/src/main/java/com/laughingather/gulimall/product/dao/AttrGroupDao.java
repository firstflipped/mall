package com.laughingather.gulimall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laughingather.gulimall.product.entity.AttrGroupEntity;
import com.laughingather.gulimall.product.entity.vo.AttrGroupVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 属性分组
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
public interface AttrGroupDao extends BaseMapper<AttrGroupEntity> {

    AttrGroupVO getAttrGroupById(@Param("attrGroupId") Long attrGroupId);

    String getGroupNameByAttrId(@Param("attrId") Long attrId);

    @Select("SELECT catelog_id FROM pms_attr_group WHERE attr_group_id = #{attrGroupId}")
    Long selectCategoryIdByAttrGroupId(Long attrGroupId);
}
