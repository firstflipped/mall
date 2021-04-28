package com.laughingather.gulimall.product;

import com.laughingather.gulimall.product.entity.to.SpuBoundTO;
import com.laughingather.gulimall.product.feign.service.CouponFeignService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class GulimallProductApplicationTests {

    @Autowired
    private CouponFeignService couponFeignService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testFeign() {
        SpuBoundTO build = SpuBoundTO.builder().spuId(1L).growBounds(BigDecimal.ONE).buyBounds(BigDecimal.TEN).build();
        Boolean aBoolean = couponFeignService.saveSpuBounds(build);
        System.out.println("执行结果" + aBoolean);
    }
}
