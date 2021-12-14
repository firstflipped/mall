package com.laughingather.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.constant.ProductConstants;
import com.laughingather.gulimall.product.dao.AttrAttrGroupRelationDao;
import com.laughingather.gulimall.product.dao.AttrDao;
import com.laughingather.gulimall.product.dao.AttrGroupDao;
import com.laughingather.gulimall.product.entity.AttrAttrGroupRelationEntity;
import com.laughingather.gulimall.product.entity.AttrEntity;
import com.laughingather.gulimall.product.entity.AttrGroupEntity;
import com.laughingather.gulimall.product.entity.dto.AttrGroupRelationDTO;
import com.laughingather.gulimall.product.entity.query.AttrAttrGroupQuery;
import com.laughingather.gulimall.product.service.AttrAttrGroupRelationService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service("attrAttrGroupRelationService")
public class AttrAttrGroupRelationServiceImpl extends ServiceImpl<AttrAttrGroupRelationDao, AttrAttrGroupRelationEntity> implements AttrAttrGroupRelationService {

    @Resource
    private AttrAttrGroupRelationDao attrAttrGroupRelationDao;
    @Resource
    private AttrDao attrDao;
    @Resource
    private AttrGroupDao attrGroupDao;

    @Override
    public List<AttrEntity> listAttrsByAttrGroupId(Long attrGroupId) {
        // 先在关联表查询出所有的attrId
        List<Long> attrIds = attrAttrGroupRelationDao.selectAttrIdsByAttrGroupId(attrGroupId);
        if (CollectionUtils.isEmpty(attrIds)) {
            return Collections.emptyList();
        }

        List<AttrEntity> attrs = attrDao.selectBatchIds(attrIds);
        return attrs;
    }

    @Override
    public MyPage<AttrEntity> pageNoAttrsByAttrGroupId(Long attrGroupId, AttrAttrGroupQuery attrAttrGroupQuery) {
        Long categoryId = attrGroupDao.selectCategoryIdByAttrGroupId(attrGroupId);

        // 查询选中的的属性id
        List<Long> attrIds = getNoMyCategoryAttrIds(categoryId);

        MyPage<AttrEntity> attrsMyPage = getAttrsWithPage(attrAttrGroupQuery, categoryId, attrIds);
        return attrsMyPage;
    }


    @Override
    public void saveBatchAttrAttrGroupRelation(AttrGroupRelationDTO[] attrGroupRelationDTOs) {
        List<AttrAttrGroupRelationEntity> attrGroupRelations = Arrays.stream(attrGroupRelationDTOs).map(item ->
                AttrAttrGroupRelationEntity.builder().attrId(item.getAttrId()).attrGroupId(item.getAttrGroupId()).build()
        ).collect(Collectors.toList());

        this.saveBatch(attrGroupRelations);
    }

    @Override
    public void deleteAttrAttrGroupRelationByAttrIdAndAttrGroupId(AttrGroupRelationDTO[] attrGroupRelationDTOs) {
        List<AttrGroupRelationDTO> attrGroupRelationDTOList = Arrays.asList(attrGroupRelationDTOs);
        attrAttrGroupRelationDao.deleteAttrAttrGroupRelationByAttrIdAndAttrGroupId(attrGroupRelationDTOList);
    }


    /**
     * 分页查询属性列表
     *
     * @param attrAttrGroupQuery
     * @param categoryId
     * @param attrIds
     * @return
     */
    private MyPage<AttrEntity> getAttrsWithPage(AttrAttrGroupQuery attrAttrGroupQuery, Long categoryId, List<Long> attrIds) {
        IPage page = new Page(attrAttrGroupQuery.getPageNumber(), attrAttrGroupQuery.getPageSize());
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(AttrEntity::getCategoryId, categoryId)
                .eq(AttrEntity::getAttrType, ProductConstants.AttrEnum.ATTR_TYPE_BASE.getCode());
        if (CollectionUtils.isNotEmpty(attrIds)) {
            queryWrapper.lambda().notIn(AttrEntity::getAttrId, attrIds);
        }
        if (StringUtils.isNotBlank(attrAttrGroupQuery.getKey())) {
            queryWrapper.and(q -> q.lambda().eq(AttrEntity::getAttrId, attrAttrGroupQuery.getKey())
                    .or().like(AttrEntity::getAttrName, attrAttrGroupQuery.getKey())
            );
        }

        MyPage<AttrEntity> attrsMyPage = MyPage.restPage(attrDao.selectPage(page, queryWrapper));
        return attrsMyPage;
    }


    /**
     * 查询选中的属性id
     *
     * @param categoryId
     * @return
     */
    private List<Long> getNoMyCategoryAttrIds(Long categoryId) {
        // 查询当前分类下的其他分组属性
        List<AttrGroupEntity> attrGroups = attrGroupDao.selectList(new QueryWrapper<AttrGroupEntity>().lambda().
                eq(AttrGroupEntity::getCategoryId, categoryId));
        List<Long> attrGroupIds = attrGroups.stream().map(item -> item.getAttrGroupId()).collect(Collectors.toList());

        List<Long> attrIds = Collections.emptyList();
        // 根据查询结果查询其关联属性
        if (CollectionUtils.isNotEmpty(attrGroupIds)) {
            List<AttrAttrGroupRelationEntity> attrAttrGroupRelations = attrAttrGroupRelationDao.selectList(new QueryWrapper<AttrAttrGroupRelationEntity>().lambda()
                    .in(AttrAttrGroupRelationEntity::getAttrGroupId, attrGroupIds));
            attrIds = attrAttrGroupRelations.stream().map(item -> item.getAttrId()).collect(Collectors.toList());
        }
        return attrIds;
    }

}