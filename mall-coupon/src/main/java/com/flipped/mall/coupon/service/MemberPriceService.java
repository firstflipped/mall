package com.flipped.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.coupon.entity.MemberPriceEntity;
import com.flipped.mall.coupon.entity.dto.SkuOtherInfoDTO;
import com.flipped.mall.coupon.entity.query.MemberPriceQuery;

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
     * @param skuOtherInfoDTO
     */
    void saveMemberPrice(SkuOtherInfoDTO skuOtherInfoDTO);

    /**
     * 分页查询会员价格列表
     *
     * @param memberPriceQuery
     * @return
     */
    MyPage<MemberPriceEntity> pageMemberPrice(MemberPriceQuery memberPriceQuery);
}

