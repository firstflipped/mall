package com.flipped.mall.ware.openapi;

import com.flipped.mall.common.entity.api.ErrorCodeEnum;
import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.ware.entity.dto.WareSkuLockDTO;
import com.flipped.mall.ware.entity.vo.FareVO;
import com.flipped.mall.ware.entity.vo.SkuHasStockVO;
import com.flipped.mall.ware.service.WareInfoService;
import com.flipped.mall.ware.service.WareSkuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 库存服务开放接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Slf4j
@RestController
@RequestMapping("/openapi/ware")
public class WareOpenApi {

    @Resource
    private WareSkuService wareSkuService;
    @Resource
    private WareInfoService wareInfoService;

    /**
     * 查询是否有库存
     *
     * @param skuIds
     * @return
     */
    @PostMapping("/stock")
    public MyResult<List<SkuHasStockVO>> getSkusHasStock(@RequestBody List<Long> skuIds) {
        List<SkuHasStockVO> skuHasStockVOList = wareSkuService.getSkusHasStock(skuIds);
        return CollectionUtils.isNotEmpty(skuHasStockVOList) ? MyResult.success(skuHasStockVOList) :
                MyResult.failed();

    }

    /**
     * 查询地址及运费信息
     *
     * @param addressId
     * @return
     */
    @GetMapping("/fare")
    public MyResult<FareVO> getFare(@RequestParam("aid") Long addressId) {
        FareVO fare = wareInfoService.getFare(addressId);
        if (fare == null) {
            return MyResult.failed();
        }
        return MyResult.success(fare);
    }

    /**
     * 订单库存锁定
     *
     * @param wareSkuLockDTO
     * @return
     */
    @PostMapping("/lock/order")
    public MyResult<Void> orderLockStock(@RequestBody WareSkuLockDTO wareSkuLockDTO) {
        try {
            Boolean result = wareSkuService.orderLockStock(wareSkuLockDTO);
            return result ? MyResult.success() : MyResult.failed();
        } catch (Exception e) {
            return MyResult.failed(ErrorCodeEnum.NO_STOCK_EXCEPTION);
        }
    }

}