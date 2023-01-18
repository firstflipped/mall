package com.laughingather.gulimall.admin.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.laughingather.gulimall.common.valid.AddGroup;
import com.laughingather.gulimall.common.valid.ListValue;
import com.laughingather.gulimall.common.valid.Phone;
import com.laughingather.gulimall.common.valid.UpdateGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 用户实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_user")
@TableName(value = "sys_user")
public class SysUserEntity {

    @Id
    @TableId
    @Null(message = "用户新增时用户id必须为空", groups = AddGroup.class)
    @NotNull(message = "用户更新时用户id不能为空", groups = UpdateGroup.class)
    private Long userid;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String username;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 密码
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "密码不能为空", groups = AddGroup.class)
    private String password;


    /**
     * 头像
     */
    @NotBlank(message = "头像不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String avatar;

    /**
     * 生日
     */
    @PastOrPresent(message = "生日不能晚于当前日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    /**
     * 性别（0：保密 1：男 2：女）
     */
    @ListValue(values = {0, 1, 2}, message = "性别不符合要求", groups = {AddGroup.class, UpdateGroup.class})
    private Integer gender;

    /**
     * 电子邮件
     */
    @NotBlank(message = "邮箱不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Email(message = "邮箱格式不正确", groups = {AddGroup.class, UpdateGroup.class})
    private String email;

    /**
     * 手机号码
     */
    @NotBlank(message = "手机号码不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Phone(message = "手机号码格式不正确", groups = {AddGroup.class, UpdateGroup.class})
    private String mobile;

    /**
     * 状态(1：正常  0：冻结 ）
     */
    @NotNull(message = "用户状态不能为空", groups = AddGroup.class)
    @ListValue(values = {1, 0}, message = "传入状态值不符合要求", groups = AddGroup.class)
    private Integer enable;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}

