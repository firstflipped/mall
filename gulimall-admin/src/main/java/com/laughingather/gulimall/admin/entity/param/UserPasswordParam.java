package com.laughingather.gulimall.admin.entity.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 用户密码修改前端传入类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-06-13 19:26:51
 */
@Data
public class UserPasswordParam {

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private Long userid;

    /**
     * 原密码
     */
    @NotBlank(message = "原密码不能为空")
    private String oldPassword;

    /**
     * 新密码
     */
    @NotBlank(message = "新密码不能为空")
    private String newPassword;

    /**
     * 新密码校验
     */
    @NotBlank(message = "校验一致性新密码不能为空")
    private String newMatchPassword;

}
