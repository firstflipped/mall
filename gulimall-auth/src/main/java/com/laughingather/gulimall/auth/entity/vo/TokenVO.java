package com.laughingather.gulimall.auth.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * token视图展示类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-09-21 10:32:40
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenVO {

    /**
     * token
     */
    private String token;


    /**
     * 刷令牌
     */
    private String refreshToken;


    /**
     * 过期时间
     */
    private Long expiresIn;

}
