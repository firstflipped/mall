package com.laughingather.gulimall.member.dao;

import com.laughingather.gulimall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 会员
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:33:47
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
