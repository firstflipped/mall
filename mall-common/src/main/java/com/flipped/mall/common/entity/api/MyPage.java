package com.flipped.mall.common.entity.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 自定义统一分页封装实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
@Builder
public class MyPage<T> {

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 总页数
     */
    private Long pages;

    /**
     * 每页记录数
     */
    private Long pageSize;

    /**
     * 当前页数
     */
    private Long pageNumber;

    /**
     * 列表数据
     */
    private List<T> list;

    public static <T> MyPage<T> restPage(IPage<T> iPage) {
        return MyPage.<T>builder()
                .total(iPage.getTotal())
                .pages(iPage.getPages())
                .pageNumber(iPage.getCurrent())
                .pageSize(iPage.getSize())
                .list(iPage.getRecords())
                .build();
    }


}
