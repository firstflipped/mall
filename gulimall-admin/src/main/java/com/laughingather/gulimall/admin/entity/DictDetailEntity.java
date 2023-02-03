package com.laughingather.gulimall.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * ${DESCRIPTION}
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2023-02-03 14:21:41
 */
@Schema
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
    @Schema(description = "字典明细id")
    private Long dictDetailId;

    /**
     * 字典明细名称
     */
    @TableField(value = "dict_detail_name")
    @Schema(description = "字典明细名称")
    private String dictDetailName;

    /**
     * 字典明细值
     */
    @TableField(value = "dict_detail_value")
    @Schema(description = "字典明细值")
    private String dictDetailValue;

    /**
     * 字典id
     */
    @TableField(value = "dict_id")
    @Schema(description = "字典id")
    private Long dictId;

    /**
     * 字典明细备注
     */
    @TableField(value = "description")
    @Schema(description = "字典明细备注")
    private String description;

    /**
     * 拼音
     */
    @TableField(value = "pinyin")
    @Schema(description = "拼音")
    private String pinyin;

    /**
     * 是否启用
     */
    @TableField(value = "`enable`")
    @Schema(description = "是否启用")
    private Integer enable;

    /**
     * 排序
     */
    @TableField(value = "sort")
    @Schema(description = "排序")
    private Integer sort;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    @Schema(description = "创建人")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    @TableField(value = "update_by")
    @Schema(description = "更新人")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    public static final String COL_DICT_DETAIL_ID = "dict_detail_id";

    public static final String COL_DICT_DETAIL_NAME = "dict_detail_name";

    public static final String COL_DICT_DETAIL_VALUE = "dict_detail_value";

    public static final String COL_DICT_ID = "dict_id";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_PINYIN = "pinyin";

    public static final String COL_ENABLE = "enable";

    public static final String COL_SORT = "sort";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_BY = "update_by";

    public static final String COL_UPDATE_TIME = "update_time";
}