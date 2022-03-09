package com.laughingather.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.product.entity.AttrGroupEntity;
import com.laughingather.gulimall.product.entity.query.AttrGroupQuery;
import com.laughingather.gulimall.product.entity.vo.AttrGroupVO;
import com.laughingather.gulimall.product.entity.vo.AttrGroupWithAttrsVO;
import com.laughingather.gulimall.product.entity.vo.SpuItemAttrGroupWithAttrVO;

import java.util.List;

/**
 * 属性分组管理逻辑接口
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
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

