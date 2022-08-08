package com.laughingather.gulimall.seckill.controller;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.seckill.entity.dto.SecKillSkuRedisDTO;
import com.laughingather.gulimall.seckill.service.SecKillSkuService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 秒杀服务路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/sec-kill")
public class SecKillController {

    @Resource
    private SecKillSkuService secKillSkuService;

    /**
     * 返回当前时间可以参与的秒杀商品信息
     *
     * @return
     */
    @GetMapping("/current-sec-kill-skus")
    public MyResult<List<SecKillSkuRedisDTO>> getCurrentSecKillSkus() {
        List<SecKillSkuRedisDTO> secKillSkuRedisDTO = secKillSkuService.getCurrentSecKillSkus();
        return CollectionUtils.isNotEmpty(secKillSkuRedisDTO) ? MyResult.success(secKillSkuRedisDTO) : MyResult.failed();
    }

}

