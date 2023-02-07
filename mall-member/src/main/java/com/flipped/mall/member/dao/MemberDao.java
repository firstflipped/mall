package com.flipped.mall.member.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flipped.mall.member.entity.MemberEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 会员
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {

    /**
     * 根据社交
     *
     * @param uid
     * @return
     */
    MemberEntity getMemberBySocialUid(@Param("uid") Long uid);
}
