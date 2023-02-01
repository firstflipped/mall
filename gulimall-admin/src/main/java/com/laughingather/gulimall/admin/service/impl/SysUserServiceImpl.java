package com.laughingather.gulimall.admin.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laughingather.gulimall.admin.entity.SysUserEntity;
import com.laughingather.gulimall.admin.entity.dto.AdminDTO;
import com.laughingather.gulimall.admin.entity.dto.AdminLoginDTO;
import com.laughingather.gulimall.admin.entity.param.UserEnableParam;
import com.laughingather.gulimall.admin.entity.param.UserPasswordParam;
import com.laughingather.gulimall.admin.mapper.SysUserMapper;
import com.laughingather.gulimall.admin.repository.SysUserRepository;
import com.laughingather.gulimall.admin.service.SysUserService;
import com.laughingather.gulimall.admin.util.SecurityUtil;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.exception.*;
import com.laughingather.gulimall.common.util.BCryptPasswordEncoderUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户逻辑实现
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private Snowflake snowflake;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysUserRepository sysUserRepository;

    /**
     * 验证码的开关，默认为 true
     */
    @Value("${gulimall.captcha.enable:false}")
    private Boolean captchaEnable;

    @Override
    public void saveUser(SysUserEntity sysUserEntity) {
        // 检验用户名、手机号、邮箱唯一性
        check(sysUserEntity);

        sysUserEntity.setUserid(snowflake.nextId());
        sysUserEntity.setPassword(BCryptPasswordEncoderUtil.encodingPassword(sysUserEntity.getPassword()));
        sysUserEntity.setCreateBy(SecurityUtil.getUsername());

        sysUserMapper.insert(sysUserEntity);
    }

    @Override
    public void updateUserById(SysUserEntity sysUserEntity) {
        // 检验用户名、手机号、邮箱唯一性
        check(sysUserEntity);

        sysUserEntity.setUpdateBy(SecurityUtil.getUsername());

        sysUserMapper.updateById(sysUserEntity);
    }

    @Override
    public void updateUserEnableById(UserEnableParam userEnableParam) {
        sysUserMapper.updateUserStatusById(userEnableParam.getUserid(), userEnableParam.getEnable());
    }

    @Override
    public void updateUserPasswordById(UserPasswordParam userPasswordParam) {
        // 校验原密码是否正确
        SysUserEntity user = sysUserRepository.getById(userPasswordParam.getUserid());
        if (!BCryptPasswordEncoderUtil.matchesPassword(userPasswordParam.getOldPassword(), user.getPassword())) {
            throw new OldPasswordCheckException();
        }

        // 校验两次新密码一致性
        if (!StringUtils.equals(userPasswordParam.getNewPassword(), userPasswordParam.getNewMatchPassword())) {
            throw new NewPasswordMatchException();
        }

        // 加密传入密码
        String password = BCryptPasswordEncoderUtil.encodingPassword(userPasswordParam.getNewPassword());
        sysUserMapper.updateUserPasswordById(userPasswordParam.getUserid(), password);
    }

    @Override
    public SysUserEntity getUserById(Long userid) {
        return sysUserMapper.selectById(userid);
    }

    @Override
    public SysUserEntity getUserByUsername(String username) {
        return sysUserRepository.getByUsernameEquals(username);
    }

    @Override
    public List<SysUserEntity> listUsers() {
        return sysUserMapper.selectList(null);
    }

    @Override
    public MyPage<SysUserEntity> listUserWithPage(Integer pageNum, Integer pageSize) {
        // 数据库分页是从0开始的
        IPage<SysUserEntity> usersWithPage = sysUserMapper.selectPage(new Page<>(pageNum, pageSize), null);

        // 组装成自己的分页信息
        return MyPage.restPage(usersWithPage);
    }

    @Override
    public AdminDTO login(AdminLoginDTO adminLoginDTO) {
        // 检验验证码
        checkCaptcha(adminLoginDTO.getCaptcha());

        SysUserEntity user = sysUserRepository.getByUsernameEquals(adminLoginDTO.getUsername());
        if (user == null) {
            throw new UserNotExistException();
        }

        if (!BCryptPasswordEncoderUtil.matchesPassword(adminLoginDTO.getPassword(), user.getPassword())) {
            return null;
        }

        return Admin2AdminDTO(user);
    }


    @Override
    public AdminDTO loginByMobile(String mobile) {
        SysUserEntity user = sysUserRepository.getByMobileEquals(mobile);
        if (user == null) {
            return null;
        }

        return Admin2AdminDTO(user);
    }


    /**
     * Admin实体转AdminTO
     *
     * @param user 用户信息
     * @return 用户信息传输
     */
    private AdminDTO Admin2AdminDTO(SysUserEntity user) {
        AdminDTO adminDTO = new AdminDTO();
        BeanUtils.copyProperties(user, adminDTO);
        return adminDTO;
    }

    /**
     * 校验用户信息
     *
     * @param sysUserEntity 用户信息
     */
    private void check(SysUserEntity sysUserEntity) {
        // 检验用户名、手机号、邮箱唯一性
        checkUsernameUnique(sysUserEntity.getUsername());
        checkMobileUnique(sysUserEntity.getMobile());
        checkEmailUnique(sysUserEntity.getEmail());
    }

    /**
     * 检验邮箱唯一性
     *
     * @param email 邮箱
     */
    private void checkEmailUnique(String email) {
        Long count = sysUserMapper.selectCount(new QueryWrapper<SysUserEntity>().lambda().eq(SysUserEntity::getEmail, email));
        if (count > 0) {
            throw new EmailExistException("email is:" + email);
        }
    }

    /**
     * 校验手机号码唯一性
     *
     * @param mobile 手机号码
     */
    private void checkMobileUnique(String mobile) {
        Long count = sysUserMapper.selectCount(new QueryWrapper<SysUserEntity>().lambda().eq(SysUserEntity::getMobile, mobile));
        if (count > 0) {
            throw new MobileExistException("mobile is:" + mobile);
        }
    }

    /**
     * 校验用户名唯一性
     *
     * @param username 用户名
     */
    private void checkUsernameUnique(String username) {
        Long count = sysUserMapper.selectCount(new QueryWrapper<SysUserEntity>().lambda().eq(SysUserEntity::getUsername, username));
        if (count > 0) {
            throw new UsernameExistException("username is:" + username);
        }
    }

    /**
     * 校验登录验证码是否一致
     *
     * @param captcha 验证码
     */
    private void checkCaptcha(String captcha) {
        // 如果验证码关闭则不进行校验
        if (!captchaEnable) {
            return;
        }

        // 校验验证码

    }

}

