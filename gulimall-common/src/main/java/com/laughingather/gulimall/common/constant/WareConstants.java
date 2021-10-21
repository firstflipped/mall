package com.laughingather.gulimall.common.constant;

import lombok.Getter;

/**
 * 库存常量类
 *
 * @author laughingather
 */
public class WareConstants {

    @Getter
    public enum PurchaseEnum {
        CREATED(0, "新建"),
        ASSIGNED(1, "已分配"),
        RECEIVE(2, "已领取"),
        FINISH(3, "已完成"),
        HASERROR(4, "有异常");

        private Integer code;
        private String message;

        PurchaseEnum(Integer code, String message) {
            this.code = code;
            this.message = message;
        }
    }

    @Getter
    public enum PurchaseDetailEnum {
        CREATED(0, "新建"),
        ASSIGNED(1, "已分配"),
        BUYING(2, "正在采购"),
        FINISH(3, "已完成"),
        HASERROR(4, "采购失败");

        private Integer code;
        private String message;

        PurchaseDetailEnum(Integer code, String message) {
            this.code = code;
            this.message = message;
        }
    }

}
