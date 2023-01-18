package com.laughingather.gulimall.admin.entity.param;

import com.laughingather.gulimall.common.valid.ListValue;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 用户状态修改前端传入类
 *
 * @author laughingather
 * @version v1.0
 * @since 2022-06-13 10:33:55
 */
@Data
public class UserEnableParam {

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private Long userid;

    /**
     * 状态(1：正常  0：冻结 ）
     */
    @NotNull(message = "用户状态不能为空")
    @ListValue(values = {1, 0}, message = "传入状态值不符合要求")
    private Integer enable;

}

