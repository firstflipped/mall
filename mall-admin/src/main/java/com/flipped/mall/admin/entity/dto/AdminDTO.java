package com.flipped.mall.admin.entity.dto;

import lombok.Data;

/**
 * 对外开放接口用户登录成功信息返回传输类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class AdminDTO {

    private Long userid;

    /**
     * 登录账号
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 电话
     */
    private String mobile;


}

