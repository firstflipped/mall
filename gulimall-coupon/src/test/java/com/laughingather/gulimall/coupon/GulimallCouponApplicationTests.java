package com.laughingather.gulimall.coupon;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
class GulimallCouponApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    public void testTime() {
        // 拼接日期区间（最近三天内）
        LocalDate now = LocalDate.now();
        LocalDate twoDayLater = now.plusDays(2);
        String startTime = LocalDateTime.of(now, LocalTime.MIN).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime endTime = LocalDateTime.of(twoDayLater, LocalTime.MAX);

        System.out.println(startTime);
        System.out.println(endTime);
    }

}
