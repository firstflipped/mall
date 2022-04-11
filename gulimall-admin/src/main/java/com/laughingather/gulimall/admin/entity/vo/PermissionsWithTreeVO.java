package com.laughingather.gulimall.admin.entity.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 权限属性视图展示
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class PermissionsWithTreeVO {

    private Long id;

    private Long parentId;

    private String permissionName;

    private LocalDateTime createTime;

    private List<PermissionsWithTreeVO> children;

}

