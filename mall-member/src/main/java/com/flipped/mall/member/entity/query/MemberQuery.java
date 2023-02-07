package com.flipped.mall.member.entity.query;

import com.flipped.mall.common.entity.query.PageQuery;
import lombok.Data;

/**
 * 会员列表查询条件实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
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
