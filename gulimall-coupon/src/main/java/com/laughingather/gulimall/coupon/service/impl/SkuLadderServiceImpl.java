package com.laughingather.gulimall.coupon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.coupon.dao.SkuLadderDao;
import com.laughingather.gulimall.coupon.entity.SkuLadderEntity;
import com.laughingather.gulimall.coupon.entity.to.SkuOtherInfoTO;
import com.laughingather.gulimall.coupon.service.SkuLadderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service("skuLadderService")
public class SkuLadderServiceImpl extends ServiceImpl<SkuLadderDao, SkuLadderEntity> implements SkuLadderService {

    @Resource
    private SkuLadderDao skuLadderDao;

    @Override
    public void saveSkuLadder(SkuOtherInfoTO skuOtherInfoTO) {
        SkuLadderEntity skuLadder = SkuLadderEntity.builder().skuId(skuOtherInfoTO.getSkuId())
                .fullCount(skuOtherInfoTO.getFullCount()).discount(skuOtherInfoTO.getDiscount())
                .addOther(skuOtherInfoTO.getCountStatus()).build();
        // 输入优惠条件才插入数据
        if (skuLadder.getFullCount() > 0) {
            skuLadderDao.insert(skuLadder);
        }
    }
}