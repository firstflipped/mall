package com.laughingather.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 属性&属性分组关联
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
@Data
@Builder
@TableName("pms_attr_attr_group_relation")
@Api(value = "属性&属性分组关联关系实体")
public class AttrAttrGroupRelationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    @ApiModelProperty(value = "属性&属性分组关联id")
    private Long id;

    /**
	 * 属性id
	 */
    @ApiModelProperty(value = "属性id")
    private Long attrId;

    /**
     * 属性分组id
     */
    @ApiModelProperty(value = "属性分组id")
    private Long attrGroupId;

    /**
     * 属性组内排序
     */
    @ApiModelProperty(value = "属性组内排序")
    private Integer attrSort;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

}
