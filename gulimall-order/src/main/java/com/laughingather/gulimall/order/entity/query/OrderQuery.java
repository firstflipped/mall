package com.laughingather.gulimall.order.entity.query;

import com.laughingather.gulimall.common.query.PageQuery;
import lombok.Data;

/**
 * 订单条件参数
 *
 * @author：laughingather
 * @create：2021-12-21 2021/12/21
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

