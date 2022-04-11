package com.laughingather.gulimall.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.member.dao.MemberStatisticsInfoDao;
import com.laughingather.gulimall.member.entity.MemberStatisticsInfoEntity;
import com.laughingather.gulimall.member.service.MemberStatisticsInfoService;
import org.springframework.stereotype.Service;


/**
 * 会员统计信息逻辑实现
 *
 * @author laughingather
 */
@Service("memberStatisticsInfoService")
public class MemberStatisticsInfoServiceImpl extends ServiceImpl<MemberStatisticsInfoDao, MemberStatisticsInfoEntity> implements MemberStatisticsInfoService {

}