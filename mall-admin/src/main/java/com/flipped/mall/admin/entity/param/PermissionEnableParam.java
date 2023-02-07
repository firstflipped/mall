package com.flipped.mall.admin.entity.param;

import com.laughingather.gulimall.common.valid.ListValue;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 权限启用/关闭前端传入类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-12-21 11:17:22
 */
@Data
public class PermissionEnableParam {

    /**
     * 用户id
     */
    @NotNull(message = "权限id不能为空")
    private Long permissionId;

    /**
     * 状态(1：正常  0：冻结 ）
     */
    @NotNull(message = "启用状态不能为空")
    @ListValue(values = {1, 0}, message = "传入启用状态值不符合要求")
    private Integer enable;

}
