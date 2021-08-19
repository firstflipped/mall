package com.laughingather.gulimall.cart.pojo.vo;

import lombok.Data;

/**
 * 用户信息展示类
 *
 * @author：laughingather
 * @create：2021-08-16 2021/8/16
 */
@Data
public class UserInfoVO {

    private Long userId;

    private String userKey;

    private Boolean tempUser = false;

}

