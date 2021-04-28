package com.laughingather.gulimall.coupon.openapi;

import com.laughingather.gulimall.coupon.entity.to.SkuOtherInfoTO;
import com.laughingather.gulimall.coupon.entity.to.SpuBoundTO;
import com.laughingather.gulimall.coupon.service.CouponOpenService;
import com.laughingather.gulimall.coupon.service.SpuBoundsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/openapi/coupon")
public class CouponOpenApi {

    @Autowired
    private SpuBoundsService spuBoundsService;
    @Autowired
    private CouponOpenService couponOpenService;

    @PostMapping("/spubounds")
    public Boolean saveSpuBounds(@RequestBody SpuBoundTO spuBoundTO) {
        spuBoundsService.saveSpuBounds(spuBoundTO);
        return true;
    }

    @PostMapping("/skuOtherInfo")
    Boolean saveSkuOtherInfo(@RequestBody SkuOtherInfoTO skuOtherInfoTO) {
        couponOpenService.saveSkuOtherInfo(skuOtherInfoTO);
        return true;
    }


}
