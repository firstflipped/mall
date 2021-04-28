package com.laughingather.gulimall.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.member.dao.MemberLevelDao;
import com.laughingather.gulimall.member.entity.MemberLevelEntity;
import com.laughingather.gulimall.member.entity.query.MemberLevelQuery;
import com.laughingather.gulimall.member.service.MemberLevelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service("memberLevelService")
public class MemberLevelServiceImpl extends ServiceImpl<MemberLevelDao, MemberLevelEntity> implements MemberLevelService {

    @Resource
    private MemberLevelDao memberLevelDao;

    @Override
    public MyPage<MemberLevelEntity> pageMemberLevels(MemberLevelQuery memberLevelQuery) {
        IPage<MemberLevelEntity> page = new Page<>(memberLevelQuery.getPageNumber(), memberLevelQuery.getPageSize());
        QueryWrapper<MemberLevelEntity> queryWrapper = null;
        if (StringUtils.isNotBlank(memberLevelQuery.getKey())) {
            queryWrapper = new QueryWrapper();
            queryWrapper.lambda().eq(MemberLevelEntity::getId, memberLevelQuery.getKey()).or()
                    .like(MemberLevelEntity::getName, memberLevelQuery.getKey());
        }

        IPage<MemberLevelEntity> memberLevelEntityIPage = memberLevelDao.selectPage(page, queryWrapper);
        return MyPage.restPage(memberLevelEntityIPage);
    }
}