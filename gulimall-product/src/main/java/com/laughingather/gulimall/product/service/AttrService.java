package com.laughingather.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.product.entity.AttrEntity;
import com.laughingather.gulimall.product.entity.dto.AttrDTO;
import com.laughingather.gulimall.product.entity.query.AttrQuery;
import com.laughingather.gulimall.product.entity.vo.AttrVO;

/**
 * 商品属性
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
public interface AttrService extends IService<AttrEntity> {

    MyPage<AttrVO> pageAttrsByCatId(Long catId, String attrType, AttrQuery attrQuery);

    void saveAttr(AttrDTO attrDTO);

    void updateAttrById(AttrDTO attrDTO);

    AttrVO getAttrVOById(Long attrId);
}

