package com.laughingather.gulimall.coupon.entity.query;

import com.laughingather.gulimall.common.query.PageQuery;
import lombok.Data;

/**
 * 秒杀条件参数
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class SecKillPromotionQuery extends PageQuery {

    private String key;

}

