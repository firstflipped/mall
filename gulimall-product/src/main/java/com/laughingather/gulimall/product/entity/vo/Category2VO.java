package com.laughingather.gulimall.product.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 二级分类视图展示类
 *
 * @author：laughingather
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category2VO {

    private String id;
    private String name;
    private String catalog1Id;
    private List<Category3VO> catalog3List;


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Category3VO {
        private String id;
        private String name;
        private String catalog2Id;
    }

}
