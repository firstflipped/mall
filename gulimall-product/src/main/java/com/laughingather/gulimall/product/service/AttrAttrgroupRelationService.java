package com.laughingather.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.laughingather.gulimall.product.entity.AttrEntity;
import com.laughingather.gulimall.product.entity.dto.AttrGroupRelationDTO;
import com.laughingather.gulimall.product.entity.query.AttrAttrGroupQuery;

import java.util.List;

/**
 * 属性&属性分组关联
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
public interface AttrAttrgroupRelationService extends IService<AttrAttrgroupRelationEntity> {

    /**
     * 根据分组id查询属性列表
     *
     * @param attrGroupId
     * @return
     */
    List<AttrEntity> listAttrsByAttrGroupId(Long attrGroupId);

    /**
     * 根据分组id查询没有关联的属性列表
     *
     * @param attrGroupId
     * @param attrAttrGroupQuery
     * @return
     */
    MyPage<AttrEntity> listNoAttrsByAttrGroupId(Long attrGroupId, AttrAttrGroupQuery attrAttrGroupQuery);

    /**
     * 增加属性分组和属性的关联关系
     *
     * @param attrGroupRelationDTOs
     */
    void saveAttrAttrgroupRelation(AttrGroupRelationDTO[] attrGroupRelationDTOs);

    /**
     * 根据分组id和属性id删除关联关系
     *
     * @param attrGroupRelationDTO
     */
    void deleteAttrAttrgroupRelationByAttrIdAndAttrGroupId(AttrGroupRelationDTO[] attrGroupRelationDTO);
}

