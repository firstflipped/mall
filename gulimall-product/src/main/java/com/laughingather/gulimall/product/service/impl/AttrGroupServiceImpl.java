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
import com.laughingather.gulimall.product.entity.vo.SpuItemAttrGroupWithAttrVO;
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
 * 属性分组管理逻辑实现
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
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

        List<AttrGroupVO> attrGroupVOList = attrGroupPage.getRecords().stream().map(this::attrGroup2AttrGroupVO).collect(Collectors.toList());

        return MyPage.<AttrGroupVO>builder().pageNumber(attrGroupQuery.getPn())
                .pageSize(attrGroupPage.getSize())
                .pages(attrGroupPage.getPages())
                .total(attrGroupPage.getTotal())
                .list(attrGroupVOList)
                .build();
    }

    @Override
    public List<AttrGroupVO> listAttrGroups() {
        return null;
    }


    @Override
    public MyPage<AttrGroupEntity> listAttrGroupsByCategoryIdWithPage(Long categoryId, AttrGroupQuery attrGroupQuery) {
        QueryWrapper<AttrGroupEntity> queryWrapper = new QueryWrapper<>();
        IPage<AttrGroupEntity> page = new Page<>(attrGroupQuery.getPn(), attrGroupQuery.getPs());

        // 组装查询条件
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

        // 查询数据库数据
        IPage<AttrGroupEntity> attrGroupPage = attrGroupDao.selectPage(page, queryWrapper);
        return MyPage.restPage(attrGroupPage);
    }


    @Override
    public AttrGroupVO getAttrGroupById(Long attrGroupId) {
        AttrGroupEntity attrGroup = attrGroupDao.selectById(attrGroupId);

        // 实体转VO
        return attrGroup2AttrGroupVO(attrGroup);
    }


    @Override
    public List<AttrGroupWithAttrsVO> getAttrGroupWithAttrsByCategoryId(Long categoryId) {
        // 先根据分类id查询所有分组
        List<AttrGroupEntity> attrGroups = attrGroupDao.selectList(new QueryWrapper<AttrGroupEntity>().lambda().eq(AttrGroupEntity::getCategoryId, categoryId));
        if (CollectionUtils.isEmpty(attrGroups)) {
            return Collections.emptyList();
        }

        // 属性分组实体转换为属性分组及属性列表VO
        return attrGroups.stream().map(this::attrGroup2AttrGroupWithAttrsVO).collect(Collectors.toList());
    }


    @Override
    public List<SpuItemAttrGroupWithAttrVO> getAttrGroupWithAttrsBySpuId(Long categoryId, Long spuId) {
        // 查出当前spu对应的所有属性的分组信息以及当前分组下的所有属性对应的值
        return attrGroupDao.getAttrGroupWithAttrsBySpuId(categoryId, spuId);
    }


    /**
     * 属性分组实体转换为属性分组VO对象
     *
     * @param attrGroup 属性分组实体
     * @return 属性分组VO对象
     */
    private AttrGroupVO attrGroup2AttrGroupVO(AttrGroupEntity attrGroup) {
        AttrGroupVO attrGroupVO = new AttrGroupVO();
        BeanUtils.copyProperties(attrGroup, attrGroupVO);

        // 获取分类名称、分类完整三级id
        Long[] categoryPath = categoryService.getCategoryPath(attrGroup.getCategoryId());
        attrGroupVO.setCategoryPath(categoryPath);
        String categoryName = categoryDao.getCategoryNameById(attrGroup.getCategoryId());
        attrGroupVO.setCategoryName(categoryName);
        return attrGroupVO;
    }


    /**
     * 属性分组实体转换为属性分组VO对象
     *
     * @param attrGroup 属性分组实体
     * @return 属性分组VO对象
     */
    private AttrGroupWithAttrsVO attrGroup2AttrGroupWithAttrsVO(AttrGroupEntity attrGroup) {
        AttrGroupWithAttrsVO attrGroupWithAttrsVO = new AttrGroupWithAttrsVO();
        BeanUtils.copyProperties(attrGroup, attrGroupWithAttrsVO);

        // 根据属性分组id查询所有属性
        List<AttrEntity> attrs = attrService.listAttrsByAttrGroupId(attrGroup.getAttrGroupId());
        attrGroupWithAttrsVO.setAttrs(attrs);

        return attrGroupWithAttrsVO;
    }


}