package com.laughingather.gulimall.seckill.openapi;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.seckill.entity.SecKillSkuRedisTO;
import com.laughingather.gulimall.seckill.service.SecKillSkuService;
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
@RequestMapping("/openapi/sec-kill")
public class SecKillOpenApi {

    @Resource
    private SecKillSkuService secKillSkuService;

    /**
     * 查询sku的秒杀信息
     *
     * @param skuId
     * @return
     */
    @GetMapping("/sec-kill-sku/{sid}/info")
    public MyResult<SecKillSkuRedisTO> getSecKillSkuInfo(@PathVariable("sid") Long skuId) {
        SecKillSkuRedisTO secKillSkuRedisTO = secKillSkuService.getSecKillSkuInfo(skuId);
        return null != secKillSkuRedisTO ? MyResult.success(secKillSkuRedisTO) : MyResult.failed();
    }

}

