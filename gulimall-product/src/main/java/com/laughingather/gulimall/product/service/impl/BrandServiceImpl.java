package com.laughingather.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.product.dao.BrandDao;
import com.laughingather.gulimall.product.entity.BrandEntity;
import com.laughingather.gulimall.product.entity.query.BrandQuery;
import com.laughingather.gulimall.product.service.BrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Resource
    private BrandDao brandDao;

    @Override
    public MyPage<BrandEntity> listBrandsWithPage(BrandQuery brandQuery) {
        IPage<BrandEntity> page = new Page<>(brandQuery.getPn(), brandQuery.getPs());
        QueryWrapper<BrandEntity> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(brandQuery.getName())) {
            queryWrapper.lambda().like(BrandEntity::getBrandName, brandQuery.getName());
        }

        IPage<BrandEntity> brandEntityIPage = brandDao.selectPage(page, queryWrapper);
        return MyPage.restPage(brandEntityIPage);
    }

    @Override
    public void saveBrand(BrandEntity brand) {
        brand.setCreateTime(LocalDateTime.now());
        brandDao.insert(brand);
    }

    /**
     * 根据id更新品牌信息，需要同步更新其他冗余数据
     *
     * @param brand
     * @return
     */
    @Override
    @Transactional
    public void updateBrandById(BrandEntity brand) {
        brand.setUpdateTime(LocalDateTime.now());
        brandDao.updateById(brand);

        // TODO：同步更新其他冗余数据
    }
}