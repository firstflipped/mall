package com.laughingather.gulimall.seckill.controller;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.seckill.entity.SecKillSkuRedisTO;
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
 * @author：laughingather
 * @create：2021-11-16 2021/11/16
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
    public MyResult getCurrentSecKillSkus() {
        List<SecKillSkuRedisTO> secKillSkuRedisTO = secKillSkuService.getCurrentSecKillSkus();
        return CollectionUtils.isNotEmpty(secKillSkuRedisTO) ? MyResult.success(secKillSkuRedisTO) : MyResult.failed();
    }

}

