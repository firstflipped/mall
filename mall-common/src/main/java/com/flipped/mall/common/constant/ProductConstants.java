package com.flipped.mall.common.constant;

import lombok.Getter;

/**
 * 商品服务常量
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public class ProductConstants {

    public static final Long CATEGORY_ROOT_ID = 0L;

    public static final String CATEGORY = "category";
    public static final String CATEGORY_LOCK = "category_lock";

    @Getter
    public enum AttrEnum {
        /**
         * 规格属性
         */
        ATTR_TYPE_BASE(1, "base", "规格属性"),
        ATTR_TYPE_SALE(0, "sale", "销售属性"),

        /**
         * 默认图片
         */
        DEFAULT_IMAGE(1, "default", "默认图片");

        private final Integer code;
        private final String type;
        private final String message;

        AttrEnum(Integer code, String type, String message) {
            this.code = code;
            this.type = type;
            this.message = message;
        }
    }

    @Getter
    public enum StatusEnum {
        /**
         * 商品状态
         */
        NEW_SPU(0, "新建"),
        SPU_UP(1, "商品上架"),
        SPU_DOWN(2, "商品下架");

        private final Integer code;
        private final String message;

        StatusEnum(Integer code, String message) {
            this.code = code;
            this.message = message;
        }
    }

}
