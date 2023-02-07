package com.flipped.mall.admin.entity.param;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 角色权限关联关系前端传入类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-20 16:55:22
 */
@Data
public class RolePermissionParam {

    /**
     * 角色id
     */
    @NotNull(message = "角色id不能为空")
    private Long roleId;

    /**
     *
     */
    private List<Long> permissionIds;

}
