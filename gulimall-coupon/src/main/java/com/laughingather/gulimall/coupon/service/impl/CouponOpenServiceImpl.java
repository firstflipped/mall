package com.laughingather.gulimall.coupon.service.impl;

import com.laughingather.gulimall.coupon.entity.to.SkuOtherInfoTO;
import com.laughingather.gulimall.coupon.service.CouponOpenService;
import com.laughingather.gulimall.coupon.service.MemberPriceService;
import com.laughingather.gulimall.coupon.service.SkuFullReductionService;
import com.laughingather.gulimall.coupon.service.SkuLadderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponOpenServiceImpl implements CouponOpenService {

    @Resource
    private SkuFullReductionService skuFullReductionService;
    @Resource
    private SkuLadderService skuLadderService;
    @Resource
    private MemberPriceService memberPriceService;

    @Override
    public void saveSkuOtherInfo(SkuOtherInfoTO skuOtherInfoTO) {
        // sku满减信息
        skuFullReductionService.saveSkuFullReduction(skuOtherInfoTO);

        // sku阶梯价格信息
        skuLadderService.saveSkuLadder(skuOtherInfoTO);

        // sku会员价格信息
        memberPriceService.saveMemberPrice(skuOtherInfoTO);
    }
}
