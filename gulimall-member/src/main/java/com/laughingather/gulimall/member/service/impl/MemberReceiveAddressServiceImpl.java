package com.laughingather.gulimall.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.member.dao.MemberReceiveAddressDao;
import com.laughingather.gulimall.member.entity.MemberReceiveAddressEntity;
import com.laughingather.gulimall.member.service.MemberReceiveAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 会员收货地址逻辑实现
 *
 * @author laughingather
 */
@Service("memberReceiveAddressService")
public class MemberReceiveAddressServiceImpl extends ServiceImpl<MemberReceiveAddressDao, MemberReceiveAddressEntity> implements MemberReceiveAddressService {

    @Resource
    private MemberReceiveAddressDao memberReceiveAddressDao;

    @Override
    public List<MemberReceiveAddressEntity> listMemberReceiveAddresses(Long memberId) {
        LambdaQueryWrapper<MemberReceiveAddressEntity> queryWrapper = new QueryWrapper<MemberReceiveAddressEntity>().lambda()
                .eq(MemberReceiveAddressEntity::getMemberId, memberId);

        return memberReceiveAddressDao.selectList(queryWrapper);
    }
}