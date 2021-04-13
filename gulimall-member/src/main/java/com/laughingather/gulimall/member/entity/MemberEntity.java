package com.laughingather.gulimall.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 会员
 * 
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:33:47
 */
@Data
@TableName("ums_member")
public class MemberEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;

	/**
	 * 会员等级id
	 */
	private Long levelId;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 昵称
	 */
	private String nickname;

	/**
	 * 手机号码
	 */
	private String mobile;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 头像
	 */
	private String header;

	/**
	 * 性别
	 */
	private Integer gender;

	/**
	 * 生日
	 */
	private LocalDate birth;

	/**
	 * 所在城市
	 */
	private String city;

	/**
	 * 职业
	 */
	private String job;

	/**
	 * 个性签名
	 */
	private String sign;

	/**
	 * 用户来源
	 */
	private Integer sourceType;

	/**
	 * 积分
	 */
	private Integer integration;

	/**
	 * 成长值
	 */
	private Integer growth;

	/**
	 * 启用状态
	 */
	private Integer status;

	/**
	 * 注册时间
	 */
	private LocalDateTime createTime;

}
