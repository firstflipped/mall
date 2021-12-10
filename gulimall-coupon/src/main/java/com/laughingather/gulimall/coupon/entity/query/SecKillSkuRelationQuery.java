package com.laughingather.gulimall.coupon.entity.query;

import com.laughingather.gulimall.common.query.PageQuery;
import lombok.Data;

/**
 * 秒杀活动关联商品条件查询参数
 *
 * @author：laughingather
 * @create：2021-11-10 2021/11/10
 */
@Data
public class SecKillSkuRelationQuery extends PageQuery {

    private String key;

    private Long promotionSessionId;

}

