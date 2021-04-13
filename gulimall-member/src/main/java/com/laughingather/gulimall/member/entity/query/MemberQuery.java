package com.laughingather.gulimall.member.entity.query;

import lombok.Data;

/**
 * @author WangJie
 */
@Data
public class MemberQuery {

    private Integer pageNumber;

    private Integer pageSize;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

}
