package com.laughingather.gulimall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laughingather.gulimall.product.entity.AttrAttrGroupRelationEntity;
import com.laughingather.gulimall.product.entity.dto.AttrGroupRelationDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 属性&属性分组关联
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
public interface AttrAttrGroupRelationDao extends BaseMapper<AttrAttrGroupRelationEntity> {

    @Select("SELECT attr_group_id FROM pms_attr_attrgroup_relation WHERE attr_id = #{attrId}")
    @ResultType(Long.class)
    Long getGroupIdByAttrId(@Param("attrId") Long attrId);

    void updateAttrAttrGroupByAttrId(AttrAttrGroupRelationEntity attrAttrgroupRelation);

    @Select("SELECT COUNT(*) FROM pms_attr_attrgroup_relation WHERE attr_id = #{attrId}")
    @ResultType(Integer.class)
    Integer countAttrAttrGroupByAttrId(@Param("attrId") Long attrId);

    @Select("SELECT attr_id FROM pms_attr_attrgroup_relation WHERE attr_group_id = #{attrGroupId}")
    @ResultType(Long.class)
    List<Long> selectAttrIdsByAttrGroupId(@Param("attrGroupId") Long attrGroupId);

    void deleteAttrAttrGroupRelationByAttrIdAndAttrGroupId(@Param("relations") List<AttrGroupRelationDTO> attrGroupRelationDTOList);
}
