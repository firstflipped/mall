package com.laughingather.gulimall.seckill.openapi;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.seckill.entity.to.SeckillSkuRedisTO;
import com.laughingather.gulimall.seckill.service.SeckillSkuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 秒杀服务对外开放接口
 *
 * @author：laughingather
 * @create：2021-11-17 2021/11/17
 */
@Slf4j
@RestController
@RequestMapping("/openapi/seckill")
public class SeckillOpenApi {

    @Resource
    private SeckillSkuService seckillSkuService;

    @GetMapping("/seckill-sku/{sid}/info")
    public MyResult<SeckillSkuRedisTO> getSeckillSkuInfo(@PathVariable("sid") Long skuId) {
        SeckillSkuRedisTO seckillSkuRedisTO = seckillSkuService.getSeckillSkuInfo(skuId);
        return null != seckillSkuRedisTO ? MyResult.success(seckillSkuRedisTO) : MyResult.failed();
    }

}

