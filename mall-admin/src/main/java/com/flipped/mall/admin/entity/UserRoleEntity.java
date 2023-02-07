package com.flipped.mall.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户角色关联实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sys_user_role")
public class UserRoleEntity {
    /**
     * 主键id
     */
    @TableId
    private Long id;

    /**
     * 用户id
     */
    private Long userid;

    /**
     * 角色id
     */
    private Long roleId;
}

