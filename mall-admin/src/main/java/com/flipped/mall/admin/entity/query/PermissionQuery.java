package com.flipped.mall.admin.entity.query;

import com.flipped.mall.common.entity.query.PageQuery;
import lombok.Data;

/**
 * 权限查询条件
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2023-02-03 23:07:52
 */
@Data
public class PermissionQuery extends PageQuery {

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 类型
     */
    private Integer type;

}
