package com.laughingather.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.laughingather.gulimall.common.valid.AddGroup;
import com.laughingather.gulimall.common.valid.ListValue;
import com.laughingather.gulimall.common.valid.UpdateGroup;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 品牌
 * 
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 品牌id
	 */
	@TableId
    @Null(message = "品牌新增id必须为空", groups = {AddGroup.class})
    @NotNull(message = "品牌修改id不能为空", groups = {UpdateGroup.class})
    private Long brandId;

    /**
     * 品牌名
     */
    @NotBlank(message = "品牌名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String brandName;

    /**
     * 检索首字母
     */
    @NotBlank(message = "品牌检索首字母不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Pattern(regexp = "^[a-zA-Z]$", message = "检索首字母不符合规范", groups = {AddGroup.class, UpdateGroup.class})
    private String firstLetter;

    /**
     * 品牌logo地址
     */
    @NotBlank(message = "品牌logo不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @URL(message = "品牌logo地址不能为非法url", groups = {AddGroup.class, UpdateGroup.class})
    private String logo;

    /**
     * 显示状态[0-不显示；1-显示]
	 */
	@NotNull(message = "品牌显示状态不能为空", groups = {AddGroup.class, UpdateGroup.class})
	@ListValue(values = {0, 1}, groups = {AddGroup.class, UpdateGroup.class})
	private Integer showStatus;

	/**
	 * 排序
	 */
	@NotNull(message = "品牌排序不能为空", groups = {AddGroup.class, UpdateGroup.class})
	@Min(value = 0, message = "品牌排序顺序小于0", groups = {AddGroup.class, UpdateGroup.class})
	private Integer sort;

	/**
	 * 介绍
	 */
	private String description;

	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;

	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;

}
