package com.laughingather.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.product.dao.AttrDao;
import com.laughingather.gulimall.product.dao.AttrGroupDao;
import com.laughingather.gulimall.product.dao.CategoryDao;
import com.laughingather.gulimall.product.entity.AttrEntity;
import com.laughingather.gulimall.product.entity.param.AttrParam;
import com.laughingather.gulimall.product.entity.query.AttrQuery;
import com.laughingather.gulimall.product.entity.vo.AttrVO;
import com.laughingather.gulimall.product.service.AttrService;
import com.laughingather.gulimall.product.service.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 属性管理逻辑实现
 *
 * @author laughingather
 */
@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Resource
    private AttrDao attrDao;
    @Resource
    private AttrGroupDao attrGroupDao;
    @Resource
    private CategoryDao categoryDao;


    @Resource
    private CategoryService categoryService;

    @Override
    public MyPage<AttrVO> listAttrsWithPage(AttrQuery attrQuery) {
        // 拼接分页条件
        IPage<AttrEntity> page = new Page<>(attrQuery.getPn(), attrQuery.getPs());

        // 拼接查询条件
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(attrQuery.getKey())) {
            queryWrapper.lambda().like(AttrEntity::getAttrName, attrQuery.getKey());
        }
        if (attrQuery.getType() != null) {
            queryWrapper.lambda().eq(AttrEntity::getAttrType, attrQuery.getType());
        }

        IPage<AttrEntity> iPage = attrDao.selectPage(page, queryWrapper);
        return assembleMyPage(iPage);
    }

    @Override
    public MyPage<AttrVO> listAttrsWithPageByCategoryId(Long categoryId, AttrQuery attrQuery) {
        // 拼接分页条件
        IPage<AttrEntity> page = new Page<>(attrQuery.getPn(), attrQuery.getPs());

        // 拼接查询条件
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<>();
        if (attrQuery.getType() != null) {
            queryWrapper.lambda().eq(AttrEntity::getAttrType, attrQuery.getType());
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
        return assembleMyPage(iPage);
    }

    @Override
    public List<AttrEntity> listAttrsByAttrGroupId(Long attrGroupId) {
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(AttrEntity::getAttrGroupId, attrGroupId);

        return attrDao.selectList(queryWrapper);
    }


    @Override
    public AttrVO getAttrVOById(Long attrId) {
        // 查询基本信息
        AttrEntity attrEntity = attrDao.selectById(attrId);

        // 查询其他信息
        return attr2AttrVO(attrEntity);
    }

    @Override
    public List<Long> selectSearchAttrIds(List<Long> attrIds) {
        return attrDao.selectSearchAttrIds(attrIds);
    }


    @Override
    public void saveAttr(AttrParam attrParam) {
        AttrEntity attr = attrParam2AttrEntity(attrParam);
        attr.setCreateTime(LocalDateTime.now());

        // 保存商品属性基本信息
        attrDao.insert(attr);
    }


    @Override
    public void updateAttrById(AttrParam attrParam) {
        AttrEntity attr = attrParam2AttrEntity(attrParam);
        attr.setUpdateTime(LocalDateTime.now());

        // 更新基本信息
        attrDao.updateById(attr);
    }


    /**
     * 获取属性的其他关联信息
     *
     * @param attrPage 属性分页列表
     * @return 属性分页列表
     */
    private MyPage<AttrVO> assembleMyPage(IPage<AttrEntity> attrPage) {

        // 设置分类和分组的名字
        List<AttrVO> attrVOList = attrPage.getRecords().stream().map(this::attr2AttrVO).collect(Collectors.toList());

        return MyPage.<AttrVO>builder().total(attrPage.getTotal()).pages(attrPage.getPages())
                .pageNumber(attrPage.getCurrent()).pageSize(attrPage.getSize()).list(attrVOList).build();
    }

    /**
     * 获取属性的分组名称和分类名称，转换为VO对象
     *
     * @param attr 属性实体
     * @return 属性VO对象
     */
    private AttrVO attr2AttrVO(AttrEntity attr) {
        // 设置分类和分组的名字
        Long[] categoryPath = categoryService.getCategoryPath(attr.getCategoryId());
        String categoryName = categoryDao.getCategoryNameById(attr.getCategoryId());
        String attrGroupName = attrGroupDao.getGroupNameByAttrId(attr.getAttrId());

        AttrVO attrVO = AttrVO.builder().categoryPath(categoryPath).categoryName(categoryName)
                .attrGroupId(attr.getAttrGroupId()).attrGroupName(attrGroupName).build();
        BeanUtils.copyProperties(attr, attrVO);

        // 把属性值拆分为列表
        attrVO.setValueSelect(Arrays.asList(attr.getValueSelect().split(";")));
        return attrVO;
    }

    /**
     * 将前端传入属性参数转换为属性实体
     *
     * @param attrParam 前端录入属性参数
     * @return 属性实体
     */
    private AttrEntity attrParam2AttrEntity(AttrParam attrParam) {
        // 设置分类和分组的名字
        AttrEntity attr = new AttrEntity();
        BeanUtils.copyProperties(attrParam, attr);
        attr.setValueSelect(String.join(";", attrParam.getValueSelect()));

        return attr;
    }
}