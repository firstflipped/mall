package com.flipped.mall.admin.entity.param;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户角色关联前端传入类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-20 15:19:52
 */
@Data
public class UserRolesParam {

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private Long userid;

    /**
     * 角色id集合
     */
    private List<Long> roleIds;

}
