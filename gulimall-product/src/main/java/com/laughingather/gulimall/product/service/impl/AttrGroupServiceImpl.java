package com.laughingather.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.product.dao.AttrAttrgroupRelationDao;
import com.laughingather.gulimall.product.dao.AttrGroupDao;
import com.laughingather.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.laughingather.gulimall.product.entity.AttrEntity;
import com.laughingather.gulimall.product.entity.AttrGroupEntity;
import com.laughingather.gulimall.product.entity.query.AttrGroupQuery;
import com.laughingather.gulimall.product.entity.vo.AttrGroupVO;
import com.laughingather.gulimall.product.entity.vo.AttrGroupWithAttrsVO;
import com.laughingather.gulimall.product.service.AttrAttrgroupRelationService;
import com.laughingather.gulimall.product.service.AttrGroupService;
import com.laughingather.gulimall.product.service.CategoryService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Resource
    private AttrGroupDao attrGroupDao;
    @Resource
    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AttrAttrgroupRelationService attrAttrgroupRelationService;


    @Override
    public MyPage<AttrGroupEntity> pageAttrGroupsByParams(Long catId, AttrGroupQuery attrGroupQuery) {
        QueryWrapper<AttrGroupEntity> queryWrapper = new QueryWrapper<>();
        IPage<AttrGroupEntity> page = new Page<>(attrGroupQuery.getPageNumber(), attrGroupQuery.getPageSize());

        if (StringUtils.isNotBlank(attrGroupQuery.getKey())) {
            queryWrapper.and(q ->
                    q.lambda().eq(AttrGroupEntity::getAttrGroupId, attrGroupQuery.getKey())
                            .or().like(AttrGroupEntity::getAttrGroupName, attrGroupQuery.getKey())
            );
        }

        // 表示需要根据catId进行查询
        if (catId != 0) {
            queryWrapper.lambda().eq(AttrGroupEntity::getCatelogId, catId);
        }

        IPage<AttrGroupEntity> attrGroupEntityIPage = attrGroupDao.selectPage(page, queryWrapper);
        return MyPage.restPage(attrGroupEntityIPage);
    }


    @Override
    public AttrGroupVO getAttrGroupById(Long attrGroupId) {
        AttrGroupVO attrGroupVO = attrGroupDao.getAttrGroupById(attrGroupId);
        // 设置分类路径
        attrGroupVO.setCatelogPath(categoryService.getCatelogPath(attrGroupVO.getCatelogId()));
        return attrGroupVO;
    }

    @Override
    public List<AttrGroupWithAttrsVO> getAttrGroupWithAttrsByCategoryId(Long categoryId) {
        // 先根据分类id查询所有分组
        List<AttrGroupEntity> attrGroups = attrGroupDao.selectList(new QueryWrapper<AttrGroupEntity>().lambda().eq(AttrGroupEntity::getCatelogId, categoryId));

        if (CollectionUtils.isEmpty(attrGroups)) {
            return Collections.emptyList();
        }

        List<AttrGroupWithAttrsVO> attrGroupWithAttrsVOs = attrGroups.stream().map(attrGroup -> {
            AttrGroupWithAttrsVO attrGroupWithAttrsVO = new AttrGroupWithAttrsVO();
            BeanUtils.copyProperties(attrGroup, attrGroupWithAttrsVO);
            List<AttrAttrgroupRelationEntity> attrgroupRelations = attrAttrgroupRelationDao.selectList(
                    new QueryWrapper<AttrAttrgroupRelationEntity>().lambda().eq(AttrAttrgroupRelationEntity::getAttrGroupId, attrGroup.getAttrGroupId()));

            // 根据分组id查询所有属性
            List<AttrEntity> attrs = attrAttrgroupRelationService.listAttrsByAttrGroupId(attrGroup.getAttrGroupId());
            attrGroupWithAttrsVO.setAttrs(attrs);
            return attrGroupWithAttrsVO;
        }).collect(Collectors.toList());

        return attrGroupWithAttrsVOs;
    }
}