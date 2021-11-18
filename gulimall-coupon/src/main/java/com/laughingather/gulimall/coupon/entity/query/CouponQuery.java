package com.laughingather.gulimall.coupon.entity.query;

import com.laughingather.gulimall.common.query.PageQuery;
import lombok.Data;

/**
 * 优惠券条件查询参数
 *
 * @author：laughingather
 * @create：2021-11-10 2021/11/10
 */
@Data
public class CouponQuery extends PageQuery {

    private String key;

}

