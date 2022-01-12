package com.laughingather.gulimall.common.constant;

import lombok.Getter;

/**
 * 库存服务常量类
 *
 * @author laughingather
 */
public class WareConstants {


    @Getter
    public enum PurchaseEnum {
        /**
         * 库存采购单状态
         */
        CREATED(0, "新建"),
        ASSIGNED(1, "已分配"),
        RECEIVE(2, "已领取"),
        FINISH(3, "已完成"),
        ERROR(4, "有异常");

        private final Integer code;
        private final String message;

        PurchaseEnum(Integer code, String message) {
            this.code = code;
            this.message = message;
        }
    }

    @Getter
    public enum PurchaseDetailEnum {
        /**
         * 库存采购单详情状态
         */
        CREATED(0, "新建"),
        ASSIGNED(1, "已分配"),
        BUYING(2, "正在采购"),
        FINISH(3, "已完成"),
        ERROR(4, "采购失败");

        private final Integer code;
        private final String message;

        PurchaseDetailEnum(Integer code, String message) {
            this.code = code;
            this.message = message;
        }
    }

    @Getter
    public enum StockLockEnum {
        /**
         * 库存锁定状态
         */
        LOCKED(0, "库存锁定"),
        UNLOCKED(1, "库存解锁"),
        SUCCESS(2, "库存扣减成功");

        private final Integer code;
        private final String message;

        StockLockEnum(Integer code, String message) {
            this.code = code;
            this.message = message;
        }
    }


    @Getter
    public enum MQEnum {
        /**
         * 消息队列
         */
        EXCHANGE("stock.event.exchange", "库存交换机"),
        DELAY_QUEUE("stock.delay.queue", "库存延时队列"),
        RELEASE_QUEUE("stock.release.stock.queue", "库存释放队列"),
        LOCKED_ROUTING_KEY("stock.locked", "库存锁定绑定关系"),
        RELEASE_ROUTING_KEY("stock.release.#", "库存释放绑定关系");

        private final String name;
        private final String describe;

        MQEnum(String name, String describe) {
            this.name = name;
            this.describe = describe;
        }
    }


}
