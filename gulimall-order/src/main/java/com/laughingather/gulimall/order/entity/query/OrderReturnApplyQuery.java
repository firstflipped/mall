package com.laughingather.gulimall.order.entity.query;

import com.laughingather.gulimall.common.query.PageQuery;
import lombok.Data;

/**
 * 订单退货申请查询条件参数
 *
 * @author：laughingather
 * @create：2021-12-21 2021/12/21
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

