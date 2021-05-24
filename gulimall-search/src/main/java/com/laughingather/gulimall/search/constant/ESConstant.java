package com.laughingather.gulimall.search.constant;

public class ESConstant {

    /**
     * sku数据在es中的索引
     */
    public static final String PRODUCT_INDEX = "gulimall_product";

    /**
     * 价格参数分隔符
     */
    public static final String PRICE_SPLIT = "-";

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
