package com.flipped.mall.seckill.openapi;

import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.seckill.entity.dto.SecKillSkuRedisDTO;
import com.flipped.mall.seckill.service.SecKillSkuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 秒杀服务对外开放接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
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
    public MyResult<SecKillSkuRedisDTO> getSecKillSkuInfo(@PathVariable("sid") Long skuId) {
        SecKillSkuRedisDTO secKillSkuRedisDTO = secKillSkuService.getSecKillSkuInfo(skuId);
        return null != secKillSkuRedisDTO ? MyResult.success(secKillSkuRedisDTO) : MyResult.failed();
    }

}

