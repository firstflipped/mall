package com.laughingather.gulimall.product.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 二级分类视图展示实体
 *
 * @author：laughingather
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category2VO {

    /**
     * 二级分类id
     */
    private String id;

    /**
     * 二级分类名称
     */
    private String name;

    /**
     * 一级分类id
     */
    private String category1Id;

    /**
     * 三级分类列表
     */
    private List<Category3VO> catalog3List;


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Category3VO {
        /**
         * 三级分类id
         */
        private String id;

        /**
         * 三级分类名称
         */
        private String name;

        /**
         * 三级分类id
         */
        private String category2Id;
    }

}
