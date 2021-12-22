package com.laughingather.gulimall.order.entity.query;

import com.laughingather.gulimall.common.query.PageQuery;
import lombok.Data;

/**
 * 退货原因查询条件参数
 *
 * @author：laughingather
 * @create：2021-12-22 2021/12/22
 */
@Data
public class OrderReturnReasonQuery extends PageQuery {

    /**
     * 状态
     */
    private Integer status;

}

