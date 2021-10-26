package com.laughingather.gulimall.common.constant;

import lombok.Getter;

/**
 * 库存常量类
 *
 * @author laughingather
 */
public class WareConstants {

    /**
     * 库存采购单枚举类
     */
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

    /**
     * 库存采购单详情枚举类
     */
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


    /**
     * 库存采购单枚举类
     */
    @Getter
    public enum StockLockEnum {
        LOCKED(0, "库存锁定"),
        UNLOCKED(1, "库存解锁"),
        SUCCESS(2, "库存扣减成功");

        private Integer code;
        private String message;

        StockLockEnum(Integer code, String message) {
            this.code = code;
            this.message = message;
        }
    }


    /**
     * 库存采购单枚举类
     */
    @Getter
    public enum MQEnum {
        EXCHANGE("stock.event.exchange", "库存交换机"),
        DELAY_QUEUE("stock.delay.queue", "库存延时队列"),
        RELEASE_QUEUE("stock.release.stock.queue", "库存释放队列"),
        LOCKED_ROUTING_KEY("stock.locked", "库存锁定绑定关系"),
        RELEASE_ROUTING_KEY("stock.release.#", "库存释放绑定关系");

        private String name;
        private String describe;

        MQEnum(String name, String describe) {
            this.name = name;
            this.describe = describe;
        }
    }


}
