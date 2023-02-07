package com.flipped.mall.order.entity.query;

import com.flipped.mall.common.entity.query.PageQuery;
import lombok.Data;

/**
 * 订单条件参数
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class OrderQuery extends PageQuery {

    /**
     * 订单号
     */
    private String orderSn;

    /**
     * 会员名
     */
    private String memberUsername;

    /**
     * 订单状态
     */
    private Integer status;

}

