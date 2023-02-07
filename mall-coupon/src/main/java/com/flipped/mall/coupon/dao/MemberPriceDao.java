package com.flipped.mall.coupon.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flipped.mall.coupon.entity.MemberPriceEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品会员价格
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Mapper
public interface MemberPriceDao extends BaseMapper<MemberPriceEntity> {

}
