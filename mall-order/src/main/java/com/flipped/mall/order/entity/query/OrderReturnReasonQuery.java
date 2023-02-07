package com.flipped.mall.order.entity.query;

import com.flipped.mall.common.entity.query.PageQuery;
import lombok.Data;

/**
 * 退货原因查询条件参数
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class OrderReturnReasonQuery extends PageQuery {

    /**
     * 状态
     */
    private Integer status;

}

