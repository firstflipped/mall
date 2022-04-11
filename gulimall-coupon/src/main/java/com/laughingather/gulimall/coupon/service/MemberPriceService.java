package com.laughingather.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.coupon.entity.MemberPriceEntity;
import com.laughingather.gulimall.coupon.entity.query.MemberPriceQuery;
import com.laughingather.gulimall.coupon.entity.to.SkuOtherInfoTO;

/**
 * 商品会员价格
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
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

