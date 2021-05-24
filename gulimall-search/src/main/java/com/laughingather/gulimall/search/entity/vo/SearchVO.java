package com.laughingather.gulimall.search.entity.vo;

import com.laughingather.gulimall.search.entity.SkuESModel;
import lombok.Data;

import java.util.List;

/**
 * 检索结果集封装
 *
 * @author WangJie
 */
@Data
public class SearchVO {

    /**
     * 商品信息
     */
    private List<SkuESModel> products;

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
    private List<CatalogVO> catalogs;

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
    public static class CatalogVO {
        private Long catalogId;
        private String catalogName;
    }
}
