package com.laughingather.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.constant.ProductConstants;
import com.laughingather.gulimall.product.dao.AttrAttrgroupRelationDao;
import com.laughingather.gulimall.product.dao.AttrDao;
import com.laughingather.gulimall.product.dao.AttrGroupDao;
import com.laughingather.gulimall.product.dao.CategoryDao;
import com.laughingather.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.laughingather.gulimall.product.entity.AttrEntity;
import com.laughingather.gulimall.product.entity.dto.AttrDTO;
import com.laughingather.gulimall.product.entity.query.AttrQuery;
import com.laughingather.gulimall.product.entity.vo.AttrVO;
import com.laughingather.gulimall.product.service.AttrService;
import com.laughingather.gulimall.product.service.CategoryService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    private static final String BASE_INFO = "base";
    private static final Integer BASE_VALUE = 1;

    @Resource
    private AttrDao attrDao;
    @Resource
    private AttrGroupDao attrGroupDao;
    @Resource
    private CategoryDao categoryDao;
    @Resource
    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Autowired
    private CategoryService categoryService;

    @Override
    public MyPage<AttrVO> listBaseAttrsByCatId(Long catId, String attrType, AttrQuery attrQuery) {

        // 拼接分页条件
        IPage page = new Page(attrQuery.getPageNumber(), attrQuery.getPageSize());

        // 拼接查询条件
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(AttrEntity::getAttrType,
                ProductConstants.AttrEnum.ATTR_TYPE_BASE.getType().equalsIgnoreCase(attrType) ?
                        ProductConstants.AttrEnum.ATTR_TYPE_BASE.getCode() : ProductConstants.AttrEnum.ATTR_TYPE_SALE.getCode());
        if (StringUtils.isNotBlank(attrQuery.getKey())) {
            queryWrapper.and(q ->
                    q.lambda().like(AttrEntity::getAttrName, attrQuery.getKey())
                            .or().eq(AttrEntity::getAttrId, attrQuery.getKey())
            );
        }
        if (catId != 0) {
            queryWrapper.lambda().eq(AttrEntity::getCatelogId, catId);
        }

        IPage<AttrEntity> iPage = attrDao.selectPage(page, queryWrapper);
        return getAttrOtherInfo(iPage);
    }

    private MyPage<AttrVO> getAttrOtherInfo(IPage<AttrEntity> iPage) {

        List<AttrVO> attrVOList = iPage.getRecords().stream().map(attr -> {
            // 设置分类和分组的名字
            AttrVO attrVO = getAttrOtherInfoById(attr);
            BeanUtils.copyProperties(attr, attrVO);

            return attrVO;
        }).collect(Collectors.toList());

        MyPage<AttrVO> myPage = MyPage.<AttrVO>builder().total(iPage.getTotal()).pages(iPage.getPages())
                .pageNumber(iPage.getCurrent()).pageSize(iPage.getSize()).list(attrVOList).build();

        return myPage;
    }

    @Override
    public AttrVO getAttrVOById(Long attrId) {
        // 查询基本信息
        AttrEntity attrEntity = attrDao.selectById(attrId);
        // 查询其他信息
        AttrVO attrVO = getAttrOtherInfoById(attrEntity);
        BeanUtils.copyProperties(attrEntity, attrVO);
        return attrVO;
    }

    private AttrVO getAttrOtherInfoById(AttrEntity attr) {
        // 设置分类和分组的名字
        Long[] catelogPath = categoryService.getCatelogPath(attr.getCatelogId());
        String categoryName = categoryDao.getNameById(attr.getCatelogId());
        Long groupId = attrAttrgroupRelationDao.getGroupIdByAttrId(attr.getAttrId());
        String groupName = attrGroupDao.getGroupNameByAttrId(attr.getAttrId());
        AttrVO attrVO = AttrVO.builder().catelogPath(catelogPath).catelogName(categoryName)
                .attrGroupId(groupId).groupName(groupName).build();
        return attrVO;
    }


    @Override
    @Transactional
    public void saveAttr(AttrDTO attrDTO) {
        AttrEntity attr = new AttrEntity();
        BeanUtils.copyProperties(attrDTO, attr);

        // 保存商品属性基本数据
        attrDao.insert(attr);
        // 保存关联关系
        saveAttrOtherInfo(attrDTO, attr);
    }

    private void saveAttrOtherInfo(AttrDTO attrDTO, AttrEntity attr) {
        if (ProductConstants.AttrEnum.ATTR_TYPE_BASE.getCode().equals(attrDTO.getAttrType())) {
            AttrAttrgroupRelationEntity attrAttrgroupRelation = AttrAttrgroupRelationEntity.builder()
                    .attrId(attr.getAttrId())
                    .attrGroupId(attrDTO.getAttrGroupId())
                    .build();
            attrAttrgroupRelationDao.insert(attrAttrgroupRelation);
        }
    }

    @Override
    @Transactional
    public void updateAttrById(AttrDTO attrDTO) {
        AttrEntity attr = new AttrEntity();
        BeanUtils.copyProperties(attrDTO, attr);
        // 更新基本信息
        attrDao.updateById(attr);

        // 更新关联信息
        updateAttrOtherInfo(attrDTO);
    }

    private void updateAttrOtherInfo(AttrDTO attrDTO) {
        if (ProductConstants.AttrEnum.ATTR_TYPE_BASE.getCode().equals(attrDTO.getAttrType())) {
            AttrAttrgroupRelationEntity attrAttrgroupRelation = AttrAttrgroupRelationEntity.builder()
                    .attrId(attrDTO.getAttrId()).attrGroupId(attrDTO.getAttrGroupId()).build();
            Integer count = attrAttrgroupRelationDao.countAttrAttrGroupByAttrId(attrDTO.getAttrId());
            if (count > 0) {
                // 更新关联信息
                attrAttrgroupRelationDao.updateAttrAttrGroupByAttrId(attrAttrgroupRelation);
            } else {
                attrAttrgroupRelationDao.insert(attrAttrgroupRelation);
            }
        }
    }

}