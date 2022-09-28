/*
Copyright [2020] [https://www.xiaonuo.vip]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Snowy采用APACHE LICENSE 2.0开源协议，您在使用过程中，需要注意以下几点：

1.请不要删除和修改根目录下的LICENSE文件。
2.请不要删除和修改Snowy源码头部的版权声明。
3.请保留源码和相关描述文件的项目出处，作者声明等。
4.分发源码时候，请注明软件出处 https://gitee.com/xiaonuobase/snowy-cloud
5.在修改包名，模块名称，项目代码等时，请注明软件出处 https://gitee.com/xiaonuobase/snowy-cloud
6.若您的项目无法满足以上几点，可申请商业授权，获取Snowy商业授权许可，请在官网购买授权，地址为 https://www.xiaonuo.vip
 */
package com.laughingather.gulimall.auth.service;

import com.laughingather.gulimall.common.entity.JwtPayLoad;

/**
 * 认证逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface AuthService {

    /**
     * 生成token
     *
     * @param jwtPayLoad jwt实体信息
     * @return token
     */
    String generateToken(JwtPayLoad jwtPayLoad);


    /**
     * 解析token获取信息
     *
     * @param token token
     * @return 用户信息
     */
    JwtPayLoad parseToken(String token);


    /**
     * 校验token
     *
     * @param token token
     * @return token是否有效
     */
    Boolean checkToken(String token);

    /**
     * 获取token有效期
     *
     * @param token token
     * @return token有效期（秒为单位）
     */
    Long getTokenExpire(String token);

}
