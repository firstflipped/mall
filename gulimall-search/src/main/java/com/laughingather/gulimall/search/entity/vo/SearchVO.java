package com.laughingather.gulimall.search.entity.vo;

import com.laughingather.gulimall.search.entity.EsSku;
import lombok.Data;

import java.util.List;

/**
 * 检索结果集封装
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class SearchVO {

    /**
     * 商品信息
     */
    private List<EsSku> products;

    /**
     * 当前页码
     */
    private Integer pageNum;

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 总页码
     */
    private Long totalPage;
    /**
     * 页面导航条
     */
    private List<Long> pageNavs;

    /**
     * 当前查询结果涉及到品牌的信息
     */
    private List<BrandVO> brands;

    /**
     * 当前查询结果涉及到属性的信息
     */
    private List<AttrVO> attrs;

    /**
     * 当前查询结果涉及到分类的信息
     */
    private List<CategoryVO> categories;

    /**
     * 面包屑
     */
    private List<NavVO> navs;

    @Data
    public static class NavVO {
        private String navName;
        private String navValue;
        private String link;
    }

    @Data
    public static class BrandVO {
        private Long brandId;
        private String brandName;
        private String brandImg;
    }

    @Data
    public static class AttrVO {
        private Long attrId;
        private String attrName;
        private List<String> attrValues;
    }

    @Data
    public static class CategoryVO {
        private Long categoryId;
        private String categoryName;
    }
}
