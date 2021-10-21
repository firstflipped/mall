package com.laughingather.gulimall.ware.openapi;

import com.laughingather.gulimall.common.api.ErrorCodeEnum;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.ware.entity.dto.WareSkuLockDTO;
import com.laughingather.gulimall.ware.entity.vo.FareVO;
import com.laughingather.gulimall.ware.entity.vo.SkuHasStockVO;
import com.laughingather.gulimall.ware.exception.NoStockException;
import com.laughingather.gulimall.ware.service.WareInfoService;
import com.laughingather.gulimall.ware.service.WareSkuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 库存服务开放接口
 *
 * @author laughingather
 */
@Slf4j
@RestController
@RequestMapping("/openapi/ware")
public class WareOpenApi {

    @Autowired
    private WareSkuService wareSkuService;
    @Autowired
    private WareInfoService wareInfoService;

    /**
     * 查询是否有库存
     *
     * @param skuIds
     * @return
     */
    @PostMapping("/hasStock")
    public List<SkuHasStockVO> getSkusHasStock(@RequestBody List<Long> skuIds) {
        List<SkuHasStockVO> skuHasStockVOS = wareSkuService.getSkusHasStock(skuIds);
        return skuHasStockVOS;
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
        return MyResult.success(fare);
    }

    /**
     * 订单库存锁定
     *
     * @param wareSkuLockDTO
     * @return
     */
    @PostMapping("/lock/order")
    public MyResult<Boolean> orderLockStock(@RequestBody WareSkuLockDTO wareSkuLockDTO) {
        try {
            Boolean result = wareSkuService.orderLockStock(wareSkuLockDTO);
            return result ? MyResult.success() : MyResult.failed();
        } catch (NoStockException e) {
            return MyResult.<Boolean>builder().code(ErrorCodeEnum.NO_STOCK_EXCEPTION.getCode()).message(ErrorCodeEnum.NO_STOCK_EXCEPTION.getMessage()).build();
        }
    }

}