package com.flipped.mall.admin.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.flipped.mall.admin.entity.CustomUserDetails;
import com.flipped.mall.admin.entity.UserEntity;
import com.flipped.mall.admin.entity.dto.AdminDTO;
import com.flipped.mall.admin.entity.dto.AdminLoginDTO;
import com.flipped.mall.admin.entity.param.UserEnableParam;
import com.flipped.mall.admin.entity.param.UserPasswordParam;
import com.flipped.mall.admin.entity.query.UserQuery;
import com.flipped.mall.admin.mapper.UserMapper;
import com.flipped.mall.admin.repository.UserRepository;
import com.flipped.mall.admin.service.UserService;
import com.flipped.mall.admin.util.SecurityUtil;
import com.flipped.mall.common.constant.AdminConstants;
import com.flipped.mall.common.constant.AuthConstants;
import com.flipped.mall.common.entity.JwtPayLoad;
import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.common.exception.*;
import com.flipped.mall.common.util.BCryptPasswordEncoderUtil;
import com.flipped.mall.common.util.JsonUtil;
import com.flipped.mall.common.util.TokenProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 用户逻辑实现
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private Snowflake snowflake;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRepository userRepository;
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private AuthenticationManager authenticationManager;

    /**
     * 验证码的开关，默认为 false
     */
    @Value("${mall.captcha.enable:False}")
    private Boolean captchaEnable;

    @Override
    public void saveUser(UserEntity userEntity) {
        // 检验用户名、手机号、邮箱唯一性
        check(userEntity);

        userEntity.setUserid(snowflake.nextId());
        userEntity.setPassword(BCryptPasswordEncoderUtil.encodingPassword(userEntity.getPassword()));
        userEntity.setCreateBy(SecurityUtil.getUsername());

        userMapper.insert(userEntity);
    }

    @Override
    public void updateUserById(UserEntity userEntity) {
        // 检验用户名、手机号、邮箱唯一性
        check(userEntity);

        userEntity.setUpdateBy(SecurityUtil.getUsername());

        userMapper.updateById(userEntity);
    }

    @Override
    public void updateUserEnableById(UserEnableParam userEnableParam) {
        userMapper.updateUserStatusById(userEnableParam.getUserid(), userEnableParam.getEnable());
    }

    @Override
    public void updateUserPasswordById(UserPasswordParam userPasswordParam) {
        // 校验原密码是否正确
        UserEntity user = userRepository.getById(userPasswordParam.getUserid());
        if (!BCryptPasswordEncoderUtil.matchesPassword(userPasswordParam.getOldPassword(), user.getPassword())) {
            throw new OldPasswordCheckException();
        }

        // 校验两次新密码一致性
        if (!StringUtils.equals(userPasswordParam.getNewPassword(), userPasswordParam.getNewMatchPassword())) {
            throw new NewPasswordMatchException();
        }

        // 加密传入密码
        String password = BCryptPasswordEncoderUtil.encodingPassword(userPasswordParam.getNewPassword());
        userMapper.updateUserPasswordById(userPasswordParam.getUserid(), password);
    }

    @Override
    public UserEntity getUserById(Long userid) {
        return userMapper.selectById(userid);
    }

    @Override
    public UserEntity getUserByUsername(String username) {
        return userRepository.getByUsernameEquals(username);
    }

    @Override
    public List<UserEntity> listUsers() {
        return userMapper.selectList(null);
    }

    @Override
    public MyPage<UserEntity> listUserWithPage(UserQuery userQuery) {
        // 分页
        IPage<UserEntity> page = Page.of(userQuery.getPn(), userQuery.getPs());
        // 查询条件
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(userQuery.getUsername())) {
            queryWrapper.like(UserEntity::getUsername, userQuery.getUsername());
        }
        if (StringUtils.isNotBlank(userQuery.getMobile())) {
            queryWrapper.like(UserEntity::getMobile, userQuery.getMobile());
        }

        IPage<UserEntity> usersPage = userMapper.selectPage(page, queryWrapper);
        // 组装成自己的分页信息
        return MyPage.restPage(usersPage);
    }

    @Override
    public AdminDTO login(AdminLoginDTO adminLoginDTO) {
        // 检验验证码
        checkCaptcha(adminLoginDTO.getCaptcha());

        UserEntity user = userRepository.getByUsernameEquals(adminLoginDTO.getUsername());
        if (user == null) {
            throw new UserNotExistException("username is: " + adminLoginDTO.getUsername());
        }

        if (!BCryptPasswordEncoderUtil.matchesPassword(adminLoginDTO.getPassword(), user.getPassword())) {
            throw new AccountPasswordInvalidException();
        }

        return Admin2AdminDTO(user);
    }


    /**
     * 登录过程只需要校验用户名密码，不需要返回权限相关信息，所以暂不调用 Spring Security 的接口验证
     *
     * @param adminLoginDTO 用户名密码传输类
     * @return 用户信息
     */
    public AdminDTO loginBySecurity(AdminLoginDTO adminLoginDTO) {
        // 检验验证码
        checkCaptcha(adminLoginDTO.getCaptcha());

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(adminLoginDTO.getUsername(), adminLoginDTO.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        if (Objects.isNull(authenticate)) {
            throw new UserNotExistException("username is: " + adminLoginDTO.getUsername());
        }

        // 查询用户权限，封装到UserDetail类中
        CustomUserDetails customUserDetails = (CustomUserDetails) authenticate.getPrincipal();
        redisTemplate.opsForValue().set(AdminConstants.ADMIN_INFO + adminLoginDTO.getUsername(),
                JsonUtil.bean2Json(customUserDetails),
                AuthConstants.TOKEN_EXP_TIME,
                TimeUnit.SECONDS);

        // 设置上下文信息
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        // 返回用户信息
        return Admin2AdminDTO(customUserDetails.getUser());
    }

    @Override
    public AdminDTO loginByMobile(String mobile) {
        UserEntity user = userRepository.getByMobileEquals(mobile);
        if (user == null) {
            return null;
        }

        return Admin2AdminDTO(user);
    }

    @Override
    public void logout(String token) {
        // 解析token
        token = token.replace(AuthConstants.TOKEN_PREFIX, "");
        JwtPayLoad jwtPayLoad = TokenProvider.getJwtPayLoad(token);
        String username = jwtPayLoad.getUsername();

        redisTemplate.delete(AdminConstants.ADMIN_INFO + username);
    }


    /**
     * Admin实体转AdminTO
     *
     * @param user 用户信息
     * @return 用户信息传输
     */
    private AdminDTO Admin2AdminDTO(UserEntity user) {
        AdminDTO adminDTO = new AdminDTO();
        BeanUtils.copyProperties(user, adminDTO);
        return adminDTO;
    }

    /**
     * 校验用户信息
     *
     * @param userEntity 用户信息
     */
    private void check(UserEntity userEntity) {
        // 检验用户名、手机号、邮箱唯一性
        checkUsernameUnique(userEntity.getUsername());
        checkMobileUnique(userEntity.getMobile());
        checkEmailUnique(userEntity.getEmail());
    }

    /**
     * 检验邮箱唯一性
     *
     * @param email 邮箱
     */
    private void checkEmailUnique(String email) {
        Long count = userMapper.selectCount(new QueryWrapper<UserEntity>().lambda().eq(UserEntity::getEmail, email));
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
        Long count = userMapper.selectCount(new QueryWrapper<UserEntity>().lambda().eq(UserEntity::getMobile, mobile));
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
        Long count = userMapper.selectCount(new QueryWrapper<UserEntity>().lambda().eq(UserEntity::getUsername, username));
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

