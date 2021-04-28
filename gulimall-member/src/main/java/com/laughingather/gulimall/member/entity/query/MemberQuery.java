package com.laughingather.gulimall.member.entity.query;

import com.laughingather.gulimall.common.query.PageQuery;
import lombok.Data;

/**
 * @author WangJie
 */
@Data
public class MemberQuery extends PageQuery {
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
