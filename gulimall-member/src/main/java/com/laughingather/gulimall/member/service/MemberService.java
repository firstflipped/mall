package com.laughingather.gulimall.member.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.member.entity.MemberEntity;
import com.laughingather.gulimall.member.entity.query.MemberQuery;

/**
 * 会员
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:33:47
 */
public interface MemberService extends IService<MemberEntity> {

    /**
     * 获取会员列表
     * @param memberQuery
     */
    IPage<MemberEntity> listMembers(MemberQuery memberQuery);
}

