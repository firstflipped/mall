package com.flipped.mall.admin.entity.query;

import com.laughingather.gulimall.common.entity.query.PageQuery;
import lombok.Data;

/**
 * 用户列表查询条件
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2023-02-03 22:19:33
 */
@Data
public class UserQuery extends PageQuery {

    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号码
     */
    private String mobile;

}
