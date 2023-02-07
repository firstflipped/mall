package com.flipped.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.product.entity.AttrGroupEntity;
import com.flipped.mall.product.entity.query.AttrGroupQuery;
import com.flipped.mall.product.entity.vo.AttrGroupVO;
import com.flipped.mall.product.entity.vo.AttrGroupWithAttrsVO;
import com.flipped.mall.product.entity.vo.SpuItemAttrGroupWithAttrVO;

import java.util.List;

/**
 * 属性分组管理逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    /**
     * 分页查询属性分组列表
     *
     * @param attrGroupQuery 属性分组列表查询条件
     * @return 属性分组列表
     */
    MyPage<AttrGroupVO> listAttrGroupsWithPage(AttrGroupQuery attrGroupQuery);

    /**
     * 查询属性分组列表
     *
     * @return 属性分组列表
     */
    List<AttrGroupVO> listAttrGroups();

    /**
     * 根据条件分页获取属性分组列表
     *
     * @param categoryId     分类id
     * @param attrGroupQuery 属性分组列表查询条件
     * @return 属性分组列表
     */
    MyPage<AttrGroupEntity> listAttrGroupsByCategoryIdWithPage(Long categoryId, AttrGroupQuery attrGroupQuery);

    /**
     * 根据id获取属性分组详细信息
     *
     * @param attrGroupId 属性分组id
     * @return 属性分组详情
     */
    AttrGroupVO getAttrGroupById(Long attrGroupId);

    /**
     * 根据分类id获取属性分组及属性值
     *
     * @param categoryId 分类id
     * @return 属性分组及属性值列表
     */
    List<AttrGroupWithAttrsVO> getAttrGroupWithAttrsByCategoryId(Long categoryId);

    /**
     * 根据skuId和分类id获取属性分组及属性值
     *
     * @param categoryId 分类id
     * @param spuId      spuId
     * @return 属性分组及属性值列表
     */
    List<SpuItemAttrGroupWithAttrVO> getAttrGroupWithAttrsBySpuId(Long categoryId, Long spuId);
}

