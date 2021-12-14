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
import com.laughingather.gulimall.product.dao.CategoryDao;
import com.laughingather.gulimall.product.entity.AttrAttrGroupRelationEntity;
import com.laughingather.gulimall.product.entity.AttrEntity;
import com.laughingather.gulimall.product.entity.dto.AttrDTO;
import com.laughingather.gulimall.product.entity.query.AttrQuery;
import com.laughingather.gulimall.product.entity.vo.AttrVO;
import com.laughingather.gulimall.product.service.AttrService;
import com.laughingather.gulimall.product.service.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Resource
    private AttrDao attrDao;
    @Resource
    private AttrGroupDao attrGroupDao;
    @Resource
    private CategoryDao categoryDao;
    @Resource
    private AttrAttrGroupRelationDao attrAttrGroupRelationDao;

    @Resource
    private CategoryService categoryService;

    @Override
    public MyPage<AttrVO> listAttrsWithPage(AttrQuery attrQuery) {
        // 拼接分页条件
        IPage page = getPage(attrQuery);

        // 拼接查询条件
        IPage<AttrEntity> iPage = attrDao.selectPage(page, null);
        return getAttrOtherInfo(iPage);
    }

    @Override
    public MyPage<AttrVO> listAttrsWithPageByCategoryId(Long categoryId, AttrQuery attrQuery) {
        IPage page = getPage(attrQuery);

        // 拼接查询条件
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<>();
        if (attrQuery.getAttrType() != null) {
            queryWrapper.lambda().eq(AttrEntity::getAttrType, attrQuery.getAttrType());
        }
        if (StringUtils.isNotBlank(attrQuery.getKey())) {
            queryWrapper.and(q ->
                    q.lambda().like(AttrEntity::getAttrName, attrQuery.getKey())
                            .or().eq(AttrEntity::getAttrId, attrQuery.getKey())
            );
        }

        // 如果分类id为0的话表示查询所有，不为0的话作为一个过滤条件
        if (!categoryId.equals(0L)) {
            queryWrapper.lambda().eq(AttrEntity::getCategoryId, categoryId);
        }

        IPage<AttrEntity> iPage = attrDao.selectPage(page, queryWrapper);
        return getAttrOtherInfo(iPage);
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

    @Override
    public List<Long> selectSearchAttrIds(List<Long> attrIds) {
        return attrDao.selectSearchAttrIds(attrIds);
    }


    @Override
    @Transactional
    public void saveAttr(AttrDTO attrDTO) {
        AttrEntity attr = new AttrEntity();
        BeanUtils.copyProperties(attrDTO, attr);

        // 保存商品属性基本数据
        attr.setCreateTime(LocalDateTime.now());
        attrDao.insert(attr);
        // 保存关联关系
        saveAttrOtherInfo(attrDTO, attr);
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

    /**
     * 保存属性关联信息
     *
     * @param attrDTO
     * @param attr
     */
    private void saveAttrOtherInfo(AttrDTO attrDTO, AttrEntity attr) {
        if (ProductConstants.AttrEnum.ATTR_TYPE_BASE.getCode().equals(attrDTO.getAttrType()) &&
                null != attrDTO.getAttrGroupId()) {
            AttrAttrGroupRelationEntity attrAttrGroupRelation = AttrAttrGroupRelationEntity.builder()
                    .attrId(attr.getAttrId())
                    .attrGroupId(attrDTO.getAttrGroupId())
                    .build();
            attrAttrGroupRelationDao.insert(attrAttrGroupRelation);
        }
    }

    /**
     * 更新属性关联信息
     *
     * @param attrDTO
     */
    private void updateAttrOtherInfo(AttrDTO attrDTO) {
        if (ProductConstants.AttrEnum.ATTR_TYPE_BASE.getCode().equals(attrDTO.getAttrType())
                && null != attrDTO.getAttrGroupId()) {
            AttrAttrGroupRelationEntity attrAttrGroupRelation = AttrAttrGroupRelationEntity.builder()
                    .attrId(attrDTO.getAttrId()).attrGroupId(attrDTO.getAttrGroupId()).build();
            Integer count = attrAttrGroupRelationDao.countAttrAttrGroupByAttrId(attrDTO.getAttrId());
            if (count > 0) {
                // 更新关联信息
                attrAttrGroupRelationDao.updateAttrAttrGroupByAttrId(attrAttrGroupRelation);
            } else {
                attrAttrGroupRelationDao.insert(attrAttrGroupRelation);
            }
        }
    }

    /**
     * 获取属性的其他关联信息
     *
     * @param iPage
     * @return
     */
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

    /**
     * 获取属性的分组名称和分类名称
     *
     * @param attr
     * @return
     */
    private AttrVO getAttrOtherInfoById(AttrEntity attr) {
        // 设置分类和分组的名字
        Long[] categoryPath = categoryService.getCategoryPath(attr.getCategoryId());
        String categoryName = categoryDao.getNameById(attr.getCategoryId());
        Long attrGroupId = attrAttrGroupRelationDao.getGroupIdByAttrId(attr.getAttrId());
        String attrGroupName = attrGroupDao.getGroupNameByAttrId(attr.getAttrId());
        AttrVO attrVO = AttrVO.builder().categoryPath(categoryPath).categoryName(categoryName)
                .attrGroupId(attrGroupId).attrGroupName(attrGroupName).build();
        return attrVO;
    }

    /**
     * 默认初始化分页数据
     *
     * @param attrQuery
     * @return
     */
    private IPage getPage(AttrQuery attrQuery) {
        // 拼接分页条件
        if (attrQuery.getPageNumber() == null || attrQuery.getPageNumber() <= 0) {
            attrQuery.setPageNumber(1);
        }
        if (attrQuery.getPageSize() == null || attrQuery.getPageSize() <= 0) {
            attrQuery.setPageSize(10);
        }
        return new Page(attrQuery.getPageNumber(), attrQuery.getPageSize());
    }

}