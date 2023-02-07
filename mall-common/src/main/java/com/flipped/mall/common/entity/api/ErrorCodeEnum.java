package com.flipped.mall.common.entity.api;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 自定义异常枚举类
 * 10xxx:系统异常
 * 11xxx:后台管理模块异常
 * 12xxx:权限模块异常
 * 13xxx:商品模块异常
 * 14xxx:订单模块异常
 * 15xxx:库存模块异常
 * 16xxx:优惠营销模块异常
 * 17xxx:秒杀模块异常
 * 18xxx:搜索模块异常
 * 19xxx:购物车模块异常
 * 20xxx:会员模块异常
 * 21xxx:第三方组件模块异常
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Getter
public enum ErrorCodeEnum {

    /**
     * 系统未知异常
     */
    UNKNOWN_EXCEPTION(10000, HttpStatus.BAD_REQUEST, "系统未知异常"),

    /**
     * 参数校验异常
     */
    PARAMS_VERIFY_EXCEPTION(10001, HttpStatus.BAD_REQUEST, "参数校验异常"),

    /**
     * 请求类型异常
     */
    REQUEST_METHOD_EXCEPTION(10002, HttpStatus.METHOD_NOT_ALLOWED, "请求类型异常"),

    /**
     * 权限校验异常
     */
    ACCESS_EXCEPTION(10003, HttpStatus.UNAUTHORIZED, "权限校验异常"),

    /**
     * 用户不存在异常
     */
    USER_NOT_EXIST_EXCEPTION(11000, HttpStatus.INTERNAL_SERVER_ERROR, "用户不存在异常"),

    /**
     * 用户名已存在异常
     */
    USERNAME_EXIST_EXCEPTION(11001, HttpStatus.INTERNAL_SERVER_ERROR, "用户名已存在异常"),

    /**
     * 手机号已存在异常
     */
    MOBILE_EXIST_EXCEPTION(11002, HttpStatus.INTERNAL_SERVER_ERROR, "手机号码已存在异常"),

    /**
     * 邮箱已存在异常
     */
    EMAIL_EXIST_EXCEPTION(11003, HttpStatus.INTERNAL_SERVER_ERROR, "邮箱已存在异常"),

    /**
     * 原密码校验失败
     */
    OLD_PASSWORD_CHECK_EXCEPTION(11004, HttpStatus.INTERNAL_SERVER_ERROR, "原密码校验失败"),

    /**
     * 新密码两次输入不一致
     */
    NEW_PASSWORD_MATCH_EXCEPTION(11005, HttpStatus.INTERNAL_SERVER_ERROR, "新密码两次输入不一致"),

    /**
     * 账号或密码错误
     */
    ACCOUNT_PASSWORD_INVALID_EXCEPTION(12000, HttpStatus.INTERNAL_SERVER_ERROR, "账号或密码错误"),

    /**
     * 社交帐号登录异常
     */
    OAUTH_LOGIN_EXCEPTION(12001, HttpStatus.INTERNAL_SERVER_ERROR, "社交帐号登录异常"),

    /**
     * 手机号登录异常
     */
    MOBILE_LOGIN_EXCEPTION(12002, HttpStatus.INTERNAL_SERVER_ERROR, "手机号登录异常"),

    /**
     * 验证码校验失败异常
     */
    SMS_CODE_CHECK_EXCEPTION(12003, HttpStatus.INTERNAL_SERVER_ERROR, "验证码校验失败异常"),

    /**
     * 验证码过期异常
     */
    SMS_CODE_EXPIRE_EXCEPTION(12004, HttpStatus.INTERNAL_SERVER_ERROR, "验证码过期异常"),

    /**
     * 商品上架异常
     */
    PRODUCT_UP_EXCEPTION(13000, HttpStatus.INTERNAL_SERVER_ERROR, "商品上架异常"),

    /**
     * 商品库存不足
     */
    NO_STOCK_EXCEPTION(14000, HttpStatus.INTERNAL_SERVER_ERROR, "商品库存不足"),

    /**
     * 验价失败
     */
    PRICE_VERIFICATION_EXCEPTION(14001, HttpStatus.INTERNAL_SERVER_ERROR, "验价失败"),

    /**
     * 提交订单令牌校验失败
     */
    TOKEN_VERIFICATION_EXCEPTION(14002, HttpStatus.INTERNAL_SERVER_ERROR, "提交订单令牌校验失败"),

    /**
     * 发送消息到MQ失败
     */
    SEND_MESSAGE_EXCEPTION(14003, HttpStatus.INTERNAL_SERVER_ERROR, "发送消息到MQ失败"),

    /**
     * 发送短信异常
     */
    SEND_SMS_EXCEPTION(21000, HttpStatus.INTERNAL_SERVER_ERROR, "发送短信异常");

    /**
     * 唯一标示异常的 code
     */
    private final Integer code;

    /**
     * HTTP 状态码
     */
    private final HttpStatus status;

    /**
     * 错误信息
     */
    private final String message;

    ErrorCodeEnum(Integer code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }
}
