package com.laughingather.gulimall.common.constant;

import lombok.Getter;

/**
 * 订单服务常量
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public class OrderConstants {

    /**
     * 订单token缓存前缀
     */
    public static final String USER_ORDER_TOKEN_PREFIX = "order:token:";

    /**
     * 订单交换器名称
     */
    public static final String EXCHANGE = "order.event.exchange";

    /**
     * 订单队列名称
     */
    public static final String DELAY_QUEUE = "order.delay.queue";

    /**
     * 释放订单队列名称
     */
    public static final String RELEASE_QUEUE = "order.release.order.queue";

    /**
     * 订单创建路由
     */
    public static final String CREATE_ROUTING_KEY = "order.create.order";

    /**
     * 订单释放路由
     */
    public static final String RELEASE_ROUTING_KEY = "order.release.order";

    /**
     *
     */
    public static final String OTHER_ROUTING_KEY = "order.release.other";

    /**
     * 差价
     */
    public static final double PRICE_DIFFERENCES = 20.00;

    /**
     * 自动收货天数
     */
    public static final int AUTO_CONFIRM_DAY = 15;

    @Getter
    public enum OrderStatusEnum {
        /**
         * 订单状态
         */
        CREATE_NEW(0, "待付款"),
        PAYED(1, "已付款"),
        DELIVERED(2, "已发货"),
        RECEIVED(3, "已收货"),
        CANCELLED(4, "已取消"),
        SERVICING(5, "售后中"),
        SERVICED(6, "售后完成");

        private final Integer code;
        private final String msg;

        OrderStatusEnum(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }

}

