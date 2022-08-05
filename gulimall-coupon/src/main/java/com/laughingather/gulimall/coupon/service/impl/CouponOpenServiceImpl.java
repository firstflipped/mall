package com.laughingather.gulimall.coupon.service.impl;

import com.laughingather.gulimall.coupon.entity.dto.SkuOtherInfoDTO;
import com.laughingather.gulimall.coupon.service.CouponOpenService;
import com.laughingather.gulimall.coupon.service.MemberPriceService;
import com.laughingather.gulimall.coupon.service.SkuFullReductionService;
import com.laughingather.gulimall.coupon.service.SkuLadderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Service
public class CouponOpenServiceImpl implements CouponOpenService {

    @Resource
    private SkuFullReductionService skuFullReductionService;
    @Resource
    private SkuLadderService skuLadderService;
    @Resource
    private MemberPriceService memberPriceService;

    @Override
    public void saveSkuOtherInfo(SkuOtherInfoDTO skuOtherInfoDTO) {
        // sku满减信息
        skuFullReductionService.saveSkuFullReduction(skuOtherInfoDTO);

        // sku阶梯价格信息
        skuLadderService.saveSkuLadder(skuOtherInfoDTO);

        // sku会员价格信息
        memberPriceService.saveMemberPrice(skuOtherInfoDTO);
    }
}
