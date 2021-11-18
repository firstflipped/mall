package com.laughingather.gulimall.coupon.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.coupon.dao.MemberPriceDao;
import com.laughingather.gulimall.coupon.entity.MemberPriceEntity;
import com.laughingather.gulimall.coupon.entity.query.MemberPriceQuery;
import com.laughingather.gulimall.coupon.entity.to.MemberPriceTO;
import com.laughingather.gulimall.coupon.entity.to.SkuOtherInfoTO;
import com.laughingather.gulimall.coupon.service.MemberPriceService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 会员价格逻辑实现
 *
 * @author laughingather
 */
@Service("memberPriceService")
public class MemberPriceServiceImpl extends ServiceImpl<MemberPriceDao, MemberPriceEntity> implements MemberPriceService {

    @Resource
    private MemberPriceDao memberPriceDao;

    @Override
    public void saveMemberPrice(SkuOtherInfoTO skuOtherInfoTO) {
        List<MemberPriceTO> memberPriceTO = skuOtherInfoTO.getMemberPriceTO();
        if (CollectionUtils.isNotEmpty(memberPriceTO)) {
            List<MemberPriceEntity> memberPrices = memberPriceTO.stream().map(item ->
                    MemberPriceEntity.builder().skuId(skuOtherInfoTO.getSkuId()).memberLevelId(item.getId())
                            .memberLevelName(item.getName()).memberPrice(item.getPrice()).build()
            ).filter(price -> price.getMemberPrice().compareTo(BigDecimal.ZERO) == 1
            ).collect(Collectors.toList());

            this.saveBatch(memberPrices);
        }
    }

    @Override
    public MyPage<MemberPriceEntity> pageMemberPrice(MemberPriceQuery memberPriceQuery) {
        if (memberPriceQuery.getPageNumber() == null || memberPriceQuery.getPageNumber() <= 0) {
            memberPriceQuery.setPageNumber(1);
        }
        if (memberPriceQuery.getPageNumber() == null || memberPriceQuery.getPageNumber() <= 0) {
            memberPriceQuery.setPageNumber(10);
        }

        IPage<MemberPriceEntity> page = new Page<>(memberPriceQuery.getPageNumber(), memberPriceQuery.getPageSize());
        IPage<MemberPriceEntity> memberPricePage = memberPriceDao.selectPage(page, null);

        return MyPage.restPage(memberPricePage);
    }
}