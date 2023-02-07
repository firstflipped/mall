package com.flipped.mall.coupon.service.impl;

import com.flipped.mall.coupon.entity.dto.SkuOtherInfoDTO;
import com.flipped.mall.coupon.service.CouponOpenService;
import com.flipped.mall.coupon.service.MemberPriceService;
import com.flipped.mall.coupon.service.SkuFullReductionService;
import com.flipped.mall.coupon.service.SkuLadderService;
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
