package com.laughingather.gulimall.order.entity.query;

import com.laughingather.gulimall.common.entity.query.PageQuery;
import lombok.Data;

/**
 * 订单退货申请查询条件参数
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class OrderReturnApplyQuery extends PageQuery {

    /**
     * 订单编号
     */
    private String orderSn;

    /**
     * 申请状态
     */
    private Integer status;

    /**
     * 退货人手机号
     */
    private String returnPhone;

    /**
     * 退货原因
     */
    private Long reasonId;

}

