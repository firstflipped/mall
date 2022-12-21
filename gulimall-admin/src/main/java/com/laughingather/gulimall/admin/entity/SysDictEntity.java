package com.laughingather.gulimall.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 字典实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-12-20 16:50:53
 */
@Data
@TableName("sys_dict")
public class SysDictEntity {

    /**
     * 主键
     */
    @TableId
    private Long dictId;

    /**
     * 字典编码
     */
    private String dictCode;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典描述
     */
    private String description;

    /**
     * 是否启用
     */
    private Integer enable;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
