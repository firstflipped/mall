package com.laughingather.gulimall.member.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.member.entity.MemberEntity;
import com.laughingather.gulimall.member.entity.dto.MemberLoginDTO;
import com.laughingather.gulimall.member.entity.dto.MemberRegisterDTO;
import com.laughingather.gulimall.member.entity.query.MemberQuery;
import com.laughingather.gulimall.member.exception.MobileExistException;
import com.laughingather.gulimall.member.exception.UsernameExistException;

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
     *
     * @param memberQuery
     */
    IPage<MemberEntity> listMembers(MemberQuery memberQuery);

    /**
     * 注册添加会员
     *
     * @param memberRegisterDTO
     * @return
     */
    void registerMember(MemberRegisterDTO memberRegisterDTO);

    /**
     * 会员登陆校验
     *
     * @param memberLoginDTO
     */
    MemberEntity checkLogin(MemberLoginDTO memberLoginDTO);

    /**
     * 检验手机号码唯一性
     *
     * @param mobile
     * @throws MobileExistException
     */
    void checkMobileUnique(String mobile) throws MobileExistException;

    /**
     * 检验邮箱唯一性
     *
     * @param email
     */
    void checkEmailUnique(String email);

    /**
     * 校验用户名唯一性
     *
     * @param username
     * @throws UsernameExistException
     */
    void checkUsernameUnique(String username) throws UsernameExistException;


}

