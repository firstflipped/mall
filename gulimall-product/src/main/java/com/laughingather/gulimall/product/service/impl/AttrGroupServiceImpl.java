package com.laughingather.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.product.dao.AttrGroupDao;
import com.laughingather.gulimall.product.dao.CategoryDao;
import com.laughingather.gulimall.product.entity.AttrEntity;
import com.laughingather.gulimall.product.entity.AttrGroupEntity;
import com.laughingather.gulimall.product.entity.query.AttrGroupQuery;
import com.laughingather.gulimall.product.entity.vo.AttrGroupVO;
import com.laughingather.gulimall.product.entity.vo.AttrGroupWithAttrsVO;
import com.laughingather.gulimall.product.entity.vo.SpuItemGroupAttrVO;
import com.laughingather.gulimall.product.service.AttrGroupService;
import com.laughingather.gulimall.product.service.AttrService;
import com.laughingather.gulimall.product.service.CategoryService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author WangJie
 */
@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Resource
    private AttrGroupDao attrGroupDao;
    @Resource
    private CategoryDao categoryDao;
    @Resource
    private CategoryService categoryService;
    @Resource
    private AttrService attrService;


    @Override
    public MyPage<AttrGroupVO> listAttrGroupsWithPage(AttrGroupQuery attrGroupQuery) {
        IPage<AttrGroupEntity> page = new Page<>(attrGroupQuery.getPn(), attrGroupQuery.getPs());
        IPage<AttrGroupEntity> attrGroupPage = attrGroupDao.selectPage(page, null);

        List<AttrGroupVO> attrGroupVOList = attrGroupPage.getRecords().stream().map(item -> {
            AttrGroupVO attrGroupVO = new AttrGroupVO();
            BeanUtils.copyProperties(item, attrGroupVO);

            // 获取分类名称
            attrGroupVO.setCategoryPath(categoryService.getCategoryPath(item.getCategoryId()));
            String categoryName = categoryDao.getCategoryNameById(item.getCategoryId());
            attrGroupVO.setCategoryName(categoryName);
            return attrGroupVO;
        }).collect(Collectors.toList());


        return MyPage.<AttrGroupVO>builder().pageNumber(attrGroupQuery.getPn())
                .pageSize(attrGroupPage.getSize())
                .pages(attrGroupPage.getPages())
                .total(attrGroupPage.getTotal())
                .list(attrGroupVOList)
                .build();
    }


    @Override
    public MyPage<AttrGroupEntity> listAttrGroupsByCategoryIdWithPage(Long categoryId, AttrGroupQuery attrGroupQuery) {
        QueryWrapper<AttrGroupEntity> queryWrapper = new QueryWrapper<>();
        IPage<AttrGroupEntity> page = new Page<>(attrGroupQuery.getPn(), attrGroupQuery.getPs());

        if (StringUtils.isNotBlank(attrGroupQuery.getKey())) {
            queryWrapper.and(q ->
                    q.lambda().eq(AttrGroupEntity::getAttrGroupId, attrGroupQuery.getKey())
                            .or().like(AttrGroupEntity::getAttrGroupName, attrGroupQuery.getKey())
            );
        }

        // 表示需要根据分类id进行查询
        if (!categoryId.equals(0L)) {
            queryWrapper.lambda().eq(AttrGroupEntity::getCategoryId, categoryId);
        }

        IPage<AttrGroupEntity> attrGroupEntityIPage = attrGroupDao.selectPage(page, queryWrapper);
        return MyPage.restPage(attrGroupEntityIPage);
    }


    @Override
    public AttrGroupVO getAttrGroupById(Long attrGroupId) {
        AttrGroupVO attrGroupVO = attrGroupDao.getAttrGroupById(attrGroupId);
        // 设置分类路径
        attrGroupVO.setCategoryPath(categoryService.getCategoryPath(attrGroupVO.getCategoryId()));
        return attrGroupVO;
    }


    @Override
    public List<AttrGroupWithAttrsVO> getAttrGroupWithAttrsByCategoryId(Long categoryId) {
        // 先根据分类id查询所有分组
        List<AttrGroupEntity> attrGroups = attrGroupDao.selectList(new QueryWrapper<AttrGroupEntity>().lambda().eq(AttrGroupEntity::getCategoryId, categoryId));

        if (CollectionUtils.isEmpty(attrGroups)) {
            return Collections.emptyList();
        }

        List<AttrGroupWithAttrsVO> attrGroupWithAttrsVOs = attrGroups.stream().map(attrGroup -> {
            AttrGroupWithAttrsVO attrGroupWithAttrsVO = new AttrGroupWithAttrsVO();
            BeanUtils.copyProperties(attrGroup, attrGroupWithAttrsVO);

            // 根据分组id查询所有属性
            List<AttrEntity> attrs = attrService.listAttrsByAttrGroupId(attrGroup.getAttrGroupId());
            attrGroupWithAttrsVO.setAttrs(attrs);
            return attrGroupWithAttrsVO;
        }).collect(Collectors.toList());

        return attrGroupWithAttrsVOs;
    }


    @Override
    public List<SpuItemGroupAttrVO> getAttrGroupWithAttrsBySpuId(Long catalogId, Long spuId) {
        // 先根据分类id查询所有分组
        List<SpuItemGroupAttrVO> attrGroupWithAttrs = attrGroupDao.getAttrGroupWithAttrsBySpuId(catalogId, spuId);

        // 查出当前spu对应的所有属性的分组信息以及当前分组下的所有属性对应的值
        return attrGroupWithAttrs;
    }

}