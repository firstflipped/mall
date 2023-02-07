package com.flipped.mall.admin.entity.query;

import com.laughingather.gulimall.common.entity.query.PageQuery;
import lombok.Data;

/**
 * 字典查询条件
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2023-02-03 21:51:16
 */
@Data
public class DictQuery extends PageQuery {

    private String dictName;

}
