package com.laughingather.gulimall.common.query;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 分页实体类
 *
 * @author laughingather
 */
@Data
public class PageQuery {

    @NotBlank(message = "当前页码不能为空")
    private Integer pageNumber;

    @NotBlank(message = "当前页条数不能为空")
    private Integer pageSize;

}
