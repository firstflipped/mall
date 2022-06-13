package com.laughingather.gulimall.admin.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laughingather.gulimall.admin.entity.SysUserEntity;
import com.laughingather.gulimall.admin.entity.param.UserStatusParam;
import com.laughingather.gulimall.admin.entity.to.AdminLoginTO;
import com.laughingather.gulimall.admin.entity.to.AdminTO;
import com.laughingather.gulimall.admin.exception.EmailExistException;
import com.laughingather.gulimall.admin.exception.MobileExistException;
import com.laughingather.gulimall.admin.exception.UsernameExistException;
import com.laughingather.gulimall.admin.mapper.SysUserMapper;
import com.laughingather.gulimall.admin.repository.SysUserRepository;
import com.laughingather.gulimall.admin.service.SysUserService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.util.BCryptPasswordEncoderUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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

    @Override
    public void saveUser(SysUserEntity sysUserEntity) {
        // 检验用户名、手机号、邮箱唯一性
        checkUsernameUnique(sysUserEntity.getUsername());
        checkMobileUnique(sysUserEntity.getMobile());
        checkEmailUnique(sysUserEntity.getEmail());

        sysUserEntity.setUserid(snowflake.nextId());
        sysUserEntity.setPassword(BCryptPasswordEncoderUtil.encodingPassword(sysUserEntity.getPassword()));
        sysUserEntity.setCreateTime(LocalDateTime.now());
        sysUserMapper.insert(sysUserEntity);
    }


    @Override
    public void updateUserById(SysUserEntity sysUserEntity) {
        sysUserEntity.setUpdateTime(LocalDateTime.now());
        sysUserMapper.updateById(sysUserEntity);
    }

    @Override
    public void updateUserStatusById(UserStatusParam userStatusParam) {
        sysUserMapper.updateUserStatusById(userStatusParam.getUserid(), userStatusParam.getStatus());
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
    public AdminTO login(AdminLoginTO adminLoginTO) {
        SysUserEntity user = sysUserRepository.getByUsernameEquals(adminLoginTO.getUsername());
        if (user == null) {
            return null;
        }

        if (!BCryptPasswordEncoderUtil.matchesPassword(adminLoginTO.getPassword(), user.getPassword())) {
            return null;
        }

        return Admin2AdminTO(user);
    }


    @Override
    public AdminTO loginByMobile(String mobile) {
        SysUserEntity user = sysUserRepository.getByPhoneEquals(mobile);
        if (user == null) {
            return null;
        }

        return Admin2AdminTO(user);
    }


    /**
     * Admin实体转AdminTO
     *
     * @param user 用户信息
     * @return 用户信息传输
     */
    private AdminTO Admin2AdminTO(SysUserEntity user) {
        AdminTO adminTO = new AdminTO();
        BeanUtils.copyProperties(user, adminTO);
        return adminTO;
    }

    /**
     * 检验邮箱唯一性
     *
     * @param email 邮箱
     */
    private void checkEmailUnique(String email) {
        Long count = sysUserMapper.selectCount(new QueryWrapper<SysUserEntity>().lambda().eq(SysUserEntity::getEmail, email));
        if (count > 0) {
            throw new EmailExistException();
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
            throw new MobileExistException();
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
            throw new UsernameExistException();
        }
    }

}

