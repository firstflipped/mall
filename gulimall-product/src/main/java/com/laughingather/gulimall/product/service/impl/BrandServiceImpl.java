package com.laughingather.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.entity.api.MyPage;
import com.laughingather.gulimall.common.constant.Constants;
import com.laughingather.gulimall.product.dao.BrandDao;
import com.laughingather.gulimall.product.entity.BrandEntity;
import com.laughingather.gulimall.product.entity.query.BrandQuery;
import com.laughingather.gulimall.product.entity.vo.BrandSelectVO;
import com.laughingather.gulimall.product.service.BrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * 品牌管理逻辑实现
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Resource
    private BrandDao brandDao;

    @Override
    public MyPage<BrandEntity> listBrandsWithPage(BrandQuery brandQuery) {
        // 组建分页、条件参数
        IPage<BrandEntity> page = new Page<>(brandQuery.getPn(), brandQuery.getPs());
        QueryWrapper<BrandEntity> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(brandQuery.getName())) {
            queryWrapper.lambda().like(BrandEntity::getBrandName, brandQuery.getName());
        }
        // 组建排序条件
        if (StringUtils.isNotBlank(brandQuery.getSort())) {
            String[] sort = brandQuery.getSort().split("-");
            queryWrapper.orderBy(true, Objects.equals(sort[1], Constants.ASC), sort[0]);
        }

        // 分页条件查询数据库
        IPage<BrandEntity> brandEntityPage = brandDao.selectPage(page, queryWrapper);
        return MyPage.restPage(brandEntityPage);
    }

    @Override
    public void saveBrand(BrandEntity brand) {
        brand.setCreateTime(LocalDateTime.now());
        brandDao.insert(brand);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBrandById(BrandEntity brand) {
        brand.setUpdateTime(LocalDateTime.now());
        brandDao.updateById(brand);

        // TODO：同步更新其他冗余数据
    }

    @Override
    public String getBrandNameByBrandId(Long brandId) {
        return brandDao.getBrandNameById(brandId);
    }

    @Override
    public List<BrandSelectVO> listBrandsWithSelect(String brandName) {
        return brandDao.listBrandsWithSelect(brandName);
    }
}