package com.laughingather.gulimall.coupon.openapi;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.coupon.entity.to.SeckillSessionTO;
import com.laughingather.gulimall.coupon.entity.to.SkuOtherInfoTO;
import com.laughingather.gulimall.coupon.entity.to.SpuBoundTO;
import com.laughingather.gulimall.coupon.service.CouponOpenService;
import com.laughingather.gulimall.coupon.service.SeckillSessionService;
import com.laughingather.gulimall.coupon.service.SpuBoundsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 优惠服务对外开放接口
 *
 * @author laughingather
 */
@Slf4j
@RestController
@RequestMapping("/openapi/coupon")
public class CouponOpenApi {

    @Resource
    private SpuBoundsService spuBoundsService;
    @Resource
    private CouponOpenService couponOpenService;
    @Resource
    private SeckillSessionService seckillSessionService;

    @PostMapping("/spubounds")
    public MyResult saveSpuBounds(@RequestBody SpuBoundTO spuBoundTO) {
        spuBoundsService.saveSpuBounds(spuBoundTO);
        return MyResult.success();
    }

    @PostMapping("/skuOtherInfo")
    public MyResult saveSkuOtherInfo(@RequestBody SkuOtherInfoTO skuOtherInfoTO) {
        couponOpenService.saveSkuOtherInfo(skuOtherInfoTO);
        return MyResult.success();
    }

    @GetMapping("/last-3days-session")
    public MyResult<List<SeckillSessionTO>> getLast3DaysSession() {
        List<SeckillSessionTO> seckillSessionTOList = seckillSessionService.getLast3DaysSession();
        return CollectionUtils.isNotEmpty(seckillSessionTOList) ? MyResult.success(seckillSessionTOList) :
                MyResult.failed();
    }

}
