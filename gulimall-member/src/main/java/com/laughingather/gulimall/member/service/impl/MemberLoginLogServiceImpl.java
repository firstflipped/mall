package com.laughingather.gulimall.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.member.dao.MemberLoginLogDao;
import com.laughingather.gulimall.member.entity.MemberLoginLogEntity;
import com.laughingather.gulimall.member.service.MemberLoginLogService;
import org.springframework.stereotype.Service;


/**
 * 会员登录记录逻辑实现
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Service("memberLoginLogService")
public class MemberLoginLogServiceImpl extends ServiceImpl<MemberLoginLogDao, MemberLoginLogEntity> implements MemberLoginLogService {

}