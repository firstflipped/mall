package com.laughingather.gulimall.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.member.dao.MemberDao;
import com.laughingather.gulimall.member.dao.MemberLevelDao;
import com.laughingather.gulimall.member.entity.MemberEntity;
import com.laughingather.gulimall.member.entity.MemberLevelEntity;
import com.laughingather.gulimall.member.entity.dto.MemberLoginDTO;
import com.laughingather.gulimall.member.entity.dto.MemberRegisterDTO;
import com.laughingather.gulimall.member.entity.query.MemberQuery;
import com.laughingather.gulimall.member.exception.MobileExistException;
import com.laughingather.gulimall.member.exception.UsernameExistException;
import com.laughingather.gulimall.member.service.MemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {

    @Autowired
    private MemberDao memberDao;
    @Autowired
    private MemberLevelDao memberLevelDao;

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

    @Override
    public void registerMember(MemberRegisterDTO memberRegisterDTO) {
        MemberEntity memberEntity = new MemberEntity();

        // 校验数据唯一性
        checkUsernameUnique(memberRegisterDTO.getUsername());
        checkMobileUnique(memberRegisterDTO.getMobile());
        memberEntity.setUsername(memberRegisterDTO.getUsername());
        memberEntity.setMobile(memberRegisterDTO.getMobile());

        // 密码加密（利用算法规则把盐值加到密文中，然后比对的时候按照算法规则取出来）
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String bCryptPassword = bCryptPasswordEncoder.encode(memberRegisterDTO.getPassword());
        memberEntity.setPassword(bCryptPassword);

        // 查询默认的会员等级
        MemberLevelEntity defaultLevel = memberLevelDao.getDefaultLevel();
        memberEntity.setLevelId(defaultLevel != null ? defaultLevel.getId() : null);
        memberEntity.setCreateTime(LocalDateTime.now());

        memberDao.insert(memberEntity);
    }

    @Override
    public MemberEntity checkLogin(MemberLoginDTO memberLoginDTO) {
        MemberEntity member = memberDao.selectOne(new QueryWrapper<MemberEntity>().lambda()
                .eq(MemberEntity::getUsername, memberLoginDTO.getUsername()).or()
                .eq(MemberEntity::getMobile, memberLoginDTO.getUsername()));

        if (member == null) {
            return null;
        }

        // 密码不匹配
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (!bCryptPasswordEncoder.matches(memberLoginDTO.getPassword(), member.getPassword())) {
            return null;
        }

        return member;
    }


    @Override
    public void checkMobileUnique(String mobile) throws MobileExistException {
        Integer count = memberDao.selectCount(new QueryWrapper<MemberEntity>().lambda().eq(MemberEntity::getMobile, mobile));
        if (count > 0) {
            throw new MobileExistException();
        }
    }

    @Override
    public void checkEmailUnique(String email) {

    }

    @Override
    public void checkUsernameUnique(String username) throws UsernameExistException {
        Integer count = memberDao.selectCount(new QueryWrapper<MemberEntity>().lambda().eq(MemberEntity::getUsername, username));
        if (count > 0) {
            throw new UsernameExistException();
        }
    }

}