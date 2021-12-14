package com.laughingather.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.product.entity.AttrGroupEntity;
import com.laughingather.gulimall.product.entity.query.AttrGroupQuery;
import com.laughingather.gulimall.product.entity.vo.AttrGroupVO;
import com.laughingather.gulimall.product.entity.vo.AttrGroupWithAttrsVO;
import com.laughingather.gulimall.product.entity.vo.SpuItemGroupAttrVO;

import java.util.List;

/**
 * 属性分组
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    /**
     * 根据条件分页获取属性分组列表
     *
     * @param categoryId
     * @param attrGroupQuery
     * @return
     */
    MyPage<AttrGroupEntity> listAttrGroupsWithPage(Long categoryId, AttrGroupQuery attrGroupQuery);

    /**
     * 根据id获取属性分组详细信息
     *
     * @param attrGroupId
     * @return
     */
    AttrGroupVO getAttrGroupById(Long attrGroupId);

    /**
     * 根据分类id获取属性分组及属性值
     *
     * @param categoryId
     * @return
     */
    List<AttrGroupWithAttrsVO> getAttrGroupWithAttrsByCategoryId(Long categoryId);

    /**
     * 根据skuId获取属性分组及属性值
     *
     * @param catalogId
     * @param spuId
     * @return
     */
    List<SpuItemGroupAttrVO> getAttrGroupWithAttrsBySpuId(Long catalogId, Long spuId);
}

