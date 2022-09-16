package com.laughingather.gulimall.ware.entity.query;

import com.laughingather.gulimall.common.entity.query.PageQuery;
import lombok.Data;

/**
 * 仓库查询条件
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class WareInfoQuery extends PageQuery {
    private String key;
}
