package com.laughingather.gulimall.common.constant;

/**
 * 订单常量
 *
 * @author：laughingather
 * @create：2021-10-19 2021/10/19
 */
public class OrderConstants {

    public static final String USER_ORDER_TOKEN_PREFIX = "order:token:";

    /**
     * 差价
     */
    public static final double PRICE_DIFFERENCES = 20.00;

    /**
     * 自动收货天数
     */
    public static final int AUTO_CONFIRM_DAY = 15;


    public enum OrderStatusEnum {
        CREATE_NEW(0, "待付款"),
        PAYED(1, "已付款"),
        SENDED(2, "已发货"),
        RECIEVED(3, "已完成"),
        CANCLED(4, "已取消"),
        SERVICING(5, "售后中"),
        SERVICED(6, "售后完成");
        private Integer code;
        private String msg;

        OrderStatusEnum(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

}

