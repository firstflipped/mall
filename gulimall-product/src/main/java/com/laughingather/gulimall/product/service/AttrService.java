package com.laughingather.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.product.entity.AttrEntity;
import com.laughingather.gulimall.product.entity.dto.AttrDTO;
import com.laughingather.gulimall.product.entity.query.AttrQuery;
import com.laughingather.gulimall.product.entity.vo.AttrVO;

import java.util.List;

/**
 * 商品属性
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
public interface AttrService extends IService<AttrEntity> {

    /**
     * 根据分类id分页查询属性列表
     *
     * @param categoryId 分类id
     * @param attrQuery
     * @return
     */
    MyPage<AttrVO> listAttrsWithPageByCategoryId(Long categoryId, AttrQuery attrQuery);

    /**
     * 保存属性信息
     *
     * @param attrDTO
     */
    void saveAttr(AttrDTO attrDTO);

    /**
     * 更新属性信息
     *
     * @param attrDTO
     */
    void updateAttrById(AttrDTO attrDTO);

    /**
     * 获取属性详情
     *
     * @param attrId 属性id
     * @return
     */
    AttrVO getAttrVOById(Long attrId);

    /**
     * 查询需要检索的属性id列表
     *
     * @param attrIds
     * @return
     */
    List<Long> selectSearchAttrIds(List<Long> attrIds);

    /**
     * 分页查询属性列表
     *
     * @param attrQuery
     * @return
     */
    MyPage<AttrVO> listAttrsWithPage(AttrQuery attrQuery);
}

