package com.laughingather.gulimall.cart.entity.vo;

import lombok.Data;

/**
 * 用户信息视图展示类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class UserInfoVO {

    private Long userId;

    private String userKey;

    private Boolean tempUser = false;

}

