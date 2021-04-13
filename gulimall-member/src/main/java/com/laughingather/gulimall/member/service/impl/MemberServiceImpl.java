package com.laughingather.gulimall.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.member.dao.MemberDao;
import com.laughingather.gulimall.member.entity.MemberEntity;
import com.laughingather.gulimall.member.entity.query.MemberQuery;
import com.laughingather.gulimall.member.service.MemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public IPage<MemberEntity> listMembers(MemberQuery memberQuery) {
        QueryWrapper<MemberEntity> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(memberQuery.getNickname())) {
            queryWrapper.lambda().like(MemberEntity::getNickname, memberQuery.getNickname());
        }
        if (StringUtils.isNotBlank(memberQuery.getEmail())) {
            queryWrapper.lambda().like(MemberEntity::getEmail, memberQuery.getEmail());
        }
        if (StringUtils.isNotBlank(memberQuery.getMobile())) {
            queryWrapper.lambda().like(MemberEntity::getMobile, memberQuery.getMobile());
        }

        Page<MemberEntity> page = new Page<>(memberQuery.getPageNumber(), memberQuery.getPageSize());
        Page<MemberEntity> memberList = memberDao.selectPage(page, queryWrapper);

        return memberList;
    }
}