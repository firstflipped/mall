package com.laughingather.gulimall.coupon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.coupon.dao.SkuFullReductionDao;
import com.laughingather.gulimall.coupon.entity.SkuFullReductionEntity;
import com.laughingather.gulimall.coupon.entity.to.SkuOtherInfoTO;
import com.laughingather.gulimall.coupon.service.SkuFullReductionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;


@Service("skuFullReductionService")
public class SkuFullReductionServiceImpl extends ServiceImpl<SkuFullReductionDao, SkuFullReductionEntity> implements SkuFullReductionService {

    @Resource
    private SkuFullReductionDao skuFullReductionDao;

    @Override
    public void saveSkuFullReduction(SkuOtherInfoTO skuOtherInfoTO) {
        SkuFullReductionEntity skuFullReduction = SkuFullReductionEntity.builder().skuId(skuOtherInfoTO.getSkuId())
                .fullPrice(skuOtherInfoTO.getFullPrice()).reducePrice(skuOtherInfoTO.getReducePrice()).build();
        if (skuFullReduction.getFullPrice().compareTo(BigDecimal.ZERO) == 1) {
            skuFullReductionDao.insert(skuFullReduction);
        }
    }
}