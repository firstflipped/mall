package com.laughingather.gulimall.adminnew.entity.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 权限属性视图展示
 *
 * @author：laughingather
 * @create：2021-12-01 2021/12/1
 */
@Data
public class PermissionsWithTreeVO {

    private Long id;

    private Long parentId;

    private String permissionName;

    private LocalDateTime createTime;

    private List<PermissionsWithTreeVO> children;

}

