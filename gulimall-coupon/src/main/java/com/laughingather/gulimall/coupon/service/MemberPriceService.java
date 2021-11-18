package com.laughingather.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.coupon.entity.MemberPriceEntity;
import com.laughingather.gulimall.coupon.entity.query.MemberPriceQuery;
import com.laughingather.gulimall.coupon.entity.to.SkuOtherInfoTO;

/**
 * 商品会员价格
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:49:52
 */
public interface MemberPriceService extends IService<MemberPriceEntity> {

    /**
     * 保存会员价格信息
     *
     * @param skuOtherInfoTO
     */
    void saveMemberPrice(SkuOtherInfoTO skuOtherInfoTO);

    /**
     * 分页查询会员价格列表
     *
     * @param memberPriceQuery
     * @return
     */
    MyPage<MemberPriceEntity> pageMemberPrice(MemberPriceQuery memberPriceQuery);
}

