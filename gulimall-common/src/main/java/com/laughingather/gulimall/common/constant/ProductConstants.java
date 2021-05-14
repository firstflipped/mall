package com.laughingather.gulimall.common.constant;

import lombok.Getter;

public class ProductConstants {

    public static final Long categoryRootId = 0L;

    @Getter
    public enum AttrEnum {
        ATTR_TYPE_BASE(1, "base", "规格参数"),
        ATTR_TYPE_SALE(0, "sale", "销售属性"),

        DEFAULT_IMAGE(1, "default", "默认图片");

        private Integer code;
        private String type;
        private String message;

        AttrEnum(Integer code, String type, String message) {
            this.code = code;
            this.type = type;
            this.message = message;
        }
    }

    @Getter
    public enum StatusEnum {
        NEW_SPU(0, "新建"),
        SPU_UP(1, "商品上架"),
        SPU_DOWN(2, "商品下架");

        private Integer code;
        private String message;

        StatusEnum(Integer code, String message) {
            this.code = code;
            this.message = message;
        }
    }

}
