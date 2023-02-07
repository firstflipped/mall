package com.flipped.mall.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flipped.mall.member.dao.MemberStatisticsInfoDao;
import com.flipped.mall.member.entity.MemberStatisticsInfoEntity;
import com.flipped.mall.member.service.MemberStatisticsInfoService;
import org.springframework.stereotype.Service;


/**
 * 会员统计信息逻辑实现
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Service("memberStatisticsInfoService")
public class MemberStatisticsInfoServiceImpl extends ServiceImpl<MemberStatisticsInfoDao, MemberStatisticsInfoEntity> implements MemberStatisticsInfoService {

}