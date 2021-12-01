package com.laughingather.gulimall.common.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 自定义分页实体
 *
 * @author laughingather
 */
@Data
@Builder
public class MyPage<T> {

    /**
     * 总记录数
     */
    private long total;
    /**
     * 总页数
     */
    private long pages;
    /**
     * 每页记录数
     */
    private long pageSize;
    /**
     * 当前页数
     */
    private long pageNumber;
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
