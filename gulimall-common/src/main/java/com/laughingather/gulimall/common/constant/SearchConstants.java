package com.laughingather.gulimall.common.constant;

/**
 * @author WangJie
 */
public class SearchConstants {

    /**
     * sku数据在es中的索引
     */
    public static final String PRODUCT_INDEX = "gulimall_product";

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
    public static final String URL = "http://search.gulimall.com/list.html?";

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
