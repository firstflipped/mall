package com.laughingather.gulimall.coupon.openapi;

import com.laughingather.gulimall.common.entity.api.MyResult;
import com.laughingather.gulimall.coupon.entity.dto.SecKillSessionDTO;
import com.laughingather.gulimall.coupon.entity.dto.SkuOtherInfoDTO;
import com.laughingather.gulimall.coupon.entity.dto.SpuBoundDTO;
import com.laughingather.gulimall.coupon.service.CouponOpenService;
import com.laughingather.gulimall.coupon.service.SecKillSessionService;
import com.laughingather.gulimall.coupon.service.SpuBoundsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 优惠服务对外开放接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
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
    private SecKillSessionService secKillSessionService;

    @PostMapping("/spu-bounds")
    public MyResult<Void> saveSpuBounds(@RequestBody SpuBoundDTO spuBoundDTO) {
        spuBoundsService.saveSpuBounds(spuBoundDTO);
        return MyResult.success();
    }

    @PostMapping("/sku-other-info")
    public MyResult<Void> saveSkuOtherInfo(@RequestBody SkuOtherInfoDTO skuOtherInfoDTO) {
        couponOpenService.saveSkuOtherInfo(skuOtherInfoDTO);
        return MyResult.success();
    }

    @GetMapping("/last-3days-session")
    public MyResult<List<SecKillSessionDTO>> getLast3DaysSession() {
        List<SecKillSessionDTO> secKillSessionDTOList = secKillSessionService.getLast3DaysSession();
        return CollectionUtils.isNotEmpty(secKillSessionDTOList) ? MyResult.success(secKillSessionDTOList) :
                MyResult.failed();
    }

}
