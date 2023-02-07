package com.flipped.mall.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flipped.mall.admin.entity.DictEntity;
import com.flipped.mall.admin.entity.query.DictQuery;
import com.flipped.mall.admin.entity.vo.DictSelectVO;
import com.flipped.mall.admin.mapper.DictDetailMapper;
import com.flipped.mall.admin.mapper.DictMapper;
import com.flipped.mall.admin.service.DictService;
import com.flipped.mall.admin.util.SecurityUtil;
import com.laughingather.gulimall.common.entity.api.MyPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典逻辑借口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2023-02-03 21:26:51
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, DictEntity> implements DictService {

    @Resource
    private DictMapper dictMapper;
    @Resource
    private DictDetailMapper dictDetailMapper;

    @Override
    public MyPage<DictEntity> listDictWithPage(DictQuery dictQuery) {
        // 分页信息
        IPage<DictEntity> page = Page.of(dictQuery.getPn(), dictQuery.getPs());
        // 查询条件
        LambdaQueryWrapper<DictEntity> queryWrapper = null;
        if (StringUtils.isNotBlank(dictQuery.getDictName())) {
            queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.like(DictEntity::getDictName, dictQuery.getDictName());
        }

        IPage<DictEntity> dictPage = dictMapper.selectPage(page, queryWrapper);
        return MyPage.restPage(dictPage);
    }

    @Override
    public List<DictSelectVO> listDictWithSelect() {
        return dictMapper.listDictWithSelect();
    }

    @Override
    public void saveDict(DictEntity dictEntity) {
        dictEntity.setCreateBy(SecurityUtil.getUsername());

        dictMapper.insert(dictEntity);
    }

    @Override
    public void updateDict(DictEntity dictEntity) {
        dictEntity.setUpdateBy(SecurityUtil.getUsername());

        dictMapper.updateById(dictEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDictById(Long dictId) {
        dictMapper.deleteById(dictId);

        // 删除字典明细表中的关联信息
        dictDetailMapper.deleteByDictId(dictId);
    }
}
