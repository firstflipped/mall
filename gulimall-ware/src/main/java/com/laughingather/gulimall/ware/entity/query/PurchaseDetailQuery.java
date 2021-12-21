package com.laughingather.gulimall.ware.entity.query;

import com.laughingather.gulimall.common.query.PageQuery;
import lombok.Data;

/**
 * 采购单详情查询条件
 *
 * @author laughingather
 */
@Data
public class PurchaseDetailQuery extends PageQuery {

    private String key;
    private Integer status;
    private Long wareId;

}
