package com.flipped.mall.common.constant;

/**
 * 搜索服务常量
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public class SearchConstants {

    /**
     * sku数据在es中的索引
     */
    public static final String PRODUCT_INDEX = "mall_product";

    /**
     * 价格参数分隔符
     */
    public static final String PRICE_SPLIT = "-";

    /**
     * 属性参数分隔符
     */
    public static final String ATTR_SPLIT = "_";

    /**
     * 间隔符参数分隔符
     */
    public static final String SPLIT = ":";

    /**
     * 间隔符参数分隔符
     */
    public static final String URL = "http://search.mall.com/list.html?";

    /**
     * 每页条数
     */
    public static final Integer PAGE_TOTAL = 10;

    /**
     * 高亮部分的前后置标签
     */
    public static final String PRE_TAGS = "<b style='color:red'>";
    public static final String POST_TAGS = "</b>";

}
