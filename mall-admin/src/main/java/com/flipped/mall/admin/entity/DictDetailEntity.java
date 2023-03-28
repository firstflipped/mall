package com.flipped.mall.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 字典明细实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2023-02-03 14:21:41
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_dict_detail")
public class DictDetailEntity {
    /**
     * 字典明细id
     */
    @TableId(value = "dict_detail_id", type = IdType.INPUT)
    private Long dictDetailId;

    /**
     * 字典明细名称
     */
    @TableField(value = "dict_detail_name")
    private String dictDetailName;

    /**
     * 字典明细值
     */
    @TableField(value = "dict_detail_value")
    private String dictDetailValue;

    /**
     * 字典id
     */
    @TableField(value = "dict_id")
    private Long dictId;

    /**
     * 字典明细备注
     */
    @TableField(value = "description")
    private String description;

    /**
     * 拼音
     */
    @TableField(value = "pinyin")
    private String pinyin;

    /**
     * 是否启用
     */
    @TableField(value = "`enable`")
    private Integer enable;

    /**
     * 排序
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

}