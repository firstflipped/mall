package com.flipped.mall.auth.service.impl;

import com.flipped.mall.auth.entity.dto.AdminLoginByMobileDTO;
import com.flipped.mall.auth.entity.dto.AdminLoginDTO;
import com.flipped.mall.auth.entity.vo.TokenVO;
import com.flipped.mall.auth.feign.entity.AdminDTO;
import com.flipped.mall.auth.feign.entity.AdminInfoDTO;
import com.flipped.mall.auth.feign.entity.AdminPermissionDTO;
import com.flipped.mall.auth.feign.service.AdminFeignService;
import com.flipped.mall.auth.service.AdminLoginService;
import com.flipped.mall.auth.service.AuthService;
import com.flipped.mall.common.constant.AuthConstants;
import com.flipped.mall.common.entity.JwtPayLoad;
import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.common.exception.SmsCodeCheckFailException;
import com.flipped.mall.common.exception.SmsCodeExpireException;
import com.flipped.mall.common.util.TokenProvider;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 管理登录逻辑实现
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Service
public class AdminLoginServiceImpl implements AdminLoginService {

    @Resource
    private AuthService authService;
    @Resource
    private AdminFeignService adminFeignService;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public TokenVO login(AdminLoginDTO adminLoginDTO) {
        MyResult<AdminDTO> adminLoginResult = adminFeignService.login(adminLoginDTO);
        // 登录失败
        if (!adminLoginResult.getSuccess()) {
            return null;
        }

        // 登录成功，生成token
        AdminDTO adminDTO = adminLoginResult.getData();
        return generateTokenVO(adminDTO);
    }

    @Override
    public TokenVO loginByMobile(AdminLoginByMobileDTO adminLoginByMobileDTO) {
        Boolean hasSmsCode = redisTemplate.hasKey(AuthConstants.SMS_CODE_CACHE_PREFIX + adminLoginByMobileDTO.getMobile());
        if (Boolean.FALSE.equals(hasSmsCode)) {
            // 验证码过期
            throw new SmsCodeExpireException();
        }

        String cacheCode = redisTemplate.opsForValue().get(AuthConstants.SMS_CODE_CACHE_PREFIX + adminLoginByMobileDTO.getMobile());
        if (!Objects.equals(adminLoginByMobileDTO.getMobile(), cacheCode)) {
            // 验证码校验失败
            throw new SmsCodeCheckFailException();
        }

        return null;
    }

    @Override
    public AdminInfoDTO getUserinfo(String token) {
        // 解析token
        Long userid = getUseridByToken(token);

        MyResult<AdminInfoDTO> getUserinfoResult = adminFeignService.getUserinfo(userid);

        // 获取用户信息为空
        if (!getUserinfoResult.getSuccess()) {
            return null;
        }

        return getUserinfoResult.getData();
    }

    @Override
    public void logout(String token) {
        adminFeignService.logout(token);
    }

    @Override
    public List<AdminPermissionDTO> getPermission(String token) {
        // 解析token
        Long userid = getUseridByToken(token);
        MyResult<List<AdminPermissionDTO>> getPermissionResult = adminFeignService.getPermission(userid);

        // 获取用户信息为空
        if (!getPermissionResult.getSuccess()) {
            return null;
        }

        return getPermissionResult.getData();
    }


    /**
     * 生成token
     *
     * @param adminDTO 管理员登录返回信息
     * @return token视图信息
     */
    private TokenVO generateTokenVO(AdminDTO adminDTO) {
        JwtPayLoad jwtPayLoad = new JwtPayLoad(adminDTO.getUserid(), adminDTO.getUsername());
        String token = AuthConstants.TOKEN_PREFIX + authService.generateToken(jwtPayLoad);
        Long tokenExpire = authService.getTokenExpire(token.replace(AuthConstants.TOKEN_PREFIX, ""));

        return TokenVO.builder().token(token).expiresIn(tokenExpire).build();
    }

    /**
     * 解析token，获取userid
     *
     * @param token
     * @return
     */
    private Long getUseridByToken(String token) {
        token = token.replace(AuthConstants.TOKEN_PREFIX, "");
        JwtPayLoad jwtPayLoad = TokenProvider.getJwtPayLoad(token);
        if (jwtPayLoad == null) {
            return null;
        }

        return jwtPayLoad.getUserid();
    }

}

