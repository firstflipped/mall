package com.laughingather.gulimall.ware.entity.query;

import com.laughingather.gulimall.common.query.PageQuery;
import lombok.Data;

/**
 * 采购单查询条件
 *
 * @author laughingather
 */
@Data
public class PurchaseQuery extends PageQuery {
    private String key;
}
