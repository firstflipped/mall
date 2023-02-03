package com.laughingather.gulimall.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2023-02-03 14:11:07
 */
@Schema
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DictEntity {
    /**
     * 字典id
     */
    @TableId
    @Schema(description = "字典id")
    private Long dictId;

    /**
     * 字典编码
     */
    @Schema(description = "字典编码")
    private String dictCode;

    /**
     * 字典名称
     */
    @Schema(description = "字典名称")
    private String dictName;

    /**
     * 是否启用
     */
    @Schema(description = "是否启用")
    private Integer enable;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;

    /**
     * 字典描述
     */
    @Schema(description = "字典描述")
    private String description;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    private String createBy;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    @Schema(description = "更新人")
    private String updateBy;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}