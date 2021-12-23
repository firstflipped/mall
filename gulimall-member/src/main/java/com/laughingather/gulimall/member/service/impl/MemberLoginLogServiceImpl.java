package com.laughingather.gulimall.member.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.laughingather.gulimall.member.dao.MemberLoginLogDao;
import com.laughingather.gulimall.member.entity.MemberLoginLogEntity;
import com.laughingather.gulimall.member.service.MemberLoginLogService;


/**
 * 会员登录记录逻辑实现
 *
 * @author laughingather
 */
@Service("memberLoginLogService")
public class MemberLoginLogServiceImpl extends ServiceImpl<MemberLoginLogDao, MemberLoginLogEntity> implements MemberLoginLogService {

}