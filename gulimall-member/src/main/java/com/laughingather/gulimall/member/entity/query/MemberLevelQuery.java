package com.laughingather.gulimall.member.entity.query;

import com.laughingather.gulimall.common.entity.query.PageQuery;
import lombok.Data;

/**
 * 会员等级列表查询条件实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class MemberLevelQuery extends PageQuery {

    private String key;

}
