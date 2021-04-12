package com.laughingather.gulimall.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.common.utils.PageUtils;
import com.laughingather.common.utils.Query;

import com.laughingather.gulimall.member.dao.MemberDao;
import com.laughingather.gulimall.member.entity.MemberEntity;
import com.laughingather.gulimall.member.service.MemberService;


@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {

}