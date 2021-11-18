package com.laughingather.gulimall.coupon.entity.query;

import com.laughingather.gulimall.common.query.PageQuery;
import lombok.Data;

/**
 * 会员价格条件参数
 *
 * @author：laughingather
 * @create：2021-11-12 2021/11/12
 */
@Data
public class MemberPriceQuery extends PageQuery {

    private String key;

}

