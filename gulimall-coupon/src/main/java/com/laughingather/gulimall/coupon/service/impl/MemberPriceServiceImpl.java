package com.laughingather.gulimall.coupon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.coupon.dao.MemberPriceDao;
import com.laughingather.gulimall.coupon.entity.MemberPriceEntity;
import com.laughingather.gulimall.coupon.entity.to.MemberPrice;
import com.laughingather.gulimall.coupon.entity.to.SkuOtherInfoTO;
import com.laughingather.gulimall.coupon.service.MemberPriceService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service("memberPriceService")
public class MemberPriceServiceImpl extends ServiceImpl<MemberPriceDao, MemberPriceEntity> implements MemberPriceService {

    @Resource
    private MemberPriceDao memberPriceDao;

    @Override
    public void saveMemberPrice(SkuOtherInfoTO skuOtherInfoTO) {
        List<MemberPrice> memberPrice = skuOtherInfoTO.getMemberPrice();
        if (CollectionUtils.isNotEmpty(memberPrice)) {
            List<MemberPriceEntity> memberPrices = memberPrice.stream().map(item ->
                    MemberPriceEntity.builder().skuId(skuOtherInfoTO.getSkuId()).memberLevelId(item.getId())
                            .memberLevelName(item.getName()).memberPrice(item.getPrice()).build()
            ).filter(price -> price.getMemberPrice().compareTo(BigDecimal.ZERO) == 1
            ).collect(Collectors.toList());

            this.saveBatch(memberPrices);
        }
    }
}