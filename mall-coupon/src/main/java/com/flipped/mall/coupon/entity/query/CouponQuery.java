package com.flipped.mall.coupon.entity.query;

import com.flipped.mall.common.entity.query.PageQuery;
import lombok.Data;

/**
 * 优惠券条件查询参数
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class CouponQuery extends PageQuery {

    private String key;

}

