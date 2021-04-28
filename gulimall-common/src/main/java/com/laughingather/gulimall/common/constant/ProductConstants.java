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

}
