package com.flipped.mall.common.constant;

/**
 * 秒杀服务常量
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public class SecKillConstants {

    public static final String SESSION_CACHE_PREFIX = "seckill:sessions:";

    public static final String SESSION_SKUS_CACHE_PREFIX = "seckill:skus:";

    public static final String SKU_STOCK_SEMAPHORE = "seckill:stock:";

    public static final String UPLOAD_LOCK = "seckill:upload:lock";

    /**
     * 锁定时间
     */
    public static final long UPLOAD_LOCK_TIME = 10;


}

