package com.laughingather.gulimall.ware.entity.query;

import com.laughingather.gulimall.common.query.PageQuery;
import lombok.Data;

/**
 * 采购单查询条件
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class PurchaseQuery extends PageQuery {
    private String key;
}
