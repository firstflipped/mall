package com.laughingather.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.entity.api.MyPage;
import com.laughingather.gulimall.product.entity.AttrEntity;
import com.laughingather.gulimall.product.entity.param.AttrParam;
import com.laughingather.gulimall.product.entity.query.AttrQuery;
import com.laughingather.gulimall.product.entity.vo.AttrVO;

import java.util.List;

/**
 * 属性管理逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface AttrService extends IService<AttrEntity> {

    /**
     * 分页查询属性列表
     *
     * @param attrQuery 属性列表查询条件
     * @return 属性列表
     */
    MyPage<AttrVO> listAttrsWithPage(AttrQuery attrQuery);

    /**
     * 根据分类id分页查询属性列表
     *
     * @param categoryId 分类id
     * @param attrQuery  属性列表查询条件
     * @return 属性列表
     */
    MyPage<AttrVO> listAttrsWithPageByCategoryId(Long categoryId, AttrQuery attrQuery);

    /**
     * 根据分组id查询属性列表
     *
     * @param attrGroupId 属性分组id
     * @return 属性列表
     */
    List<AttrEntity> listAttrsByAttrGroupId(Long attrGroupId);

    /**
     * 保存属性信息
     *
     * @param attrParam 新增属性信息
     */
    void saveAttr(AttrParam attrParam);

    /**
     * 更新属性信息
     *
     * @param attrParam 修改属性信息
     */
    void updateAttrById(AttrParam attrParam);

    /**
     * 获取属性详情
     *
     * @param attrId 属性id
     * @return 属性详情
     */
    AttrVO getAttrVOById(Long attrId);

    /**
     * 查询需要检索的属性id列表
     *
     * @param attrIds 属性id列表
     * @return 属性id列表
     */
    List<Long> selectSearchAttrIds(List<Long> attrIds);
}

