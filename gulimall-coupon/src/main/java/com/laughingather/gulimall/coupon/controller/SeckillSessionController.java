package com.laughingather.gulimall.coupon.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.coupon.entity.SeckillSessionEntity;
import com.laughingather.gulimall.coupon.entity.query.SeckillSessionQuery;
import com.laughingather.gulimall.coupon.service.SeckillSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


/**
 * 秒杀活动场次
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:49:52
 */
@RestController
@RequestMapping("/coupon/seckillsession")
public class SeckillSessionController {
    @Resource
    private SeckillSessionService seckillSessionService;

    @GetMapping("/page")
    public MyResult<MyPage<SeckillSessionEntity>> pageSeckillSession(@ModelAttribute SeckillSessionQuery seckillSessionQuery) {
        MyPage<SeckillSessionEntity> seckillSessionPage = seckillSessionService.pageSeckillSession(seckillSessionQuery);
        return MyResult.success(seckillSessionPage);
    }

    @GetMapping("/info/{id}")
    public MyResult<SeckillSessionEntity> getSeckillSessionById(@PathVariable Long id) {
        SeckillSessionEntity seckillSession = seckillSessionService.getById(id);
        return MyResult.success(seckillSession);
    }

    @PostMapping
    public MyResult saveSeckillSession(@RequestBody SeckillSessionEntity seckillSession) {
        seckillSession.setCreateTime(LocalDateTime.now());
        seckillSessionService.save(seckillSession);
        return MyResult.success();
    }

    @PutMapping
    public MyResult updateSeckillSessionById(@RequestBody SeckillSessionEntity seckillSession) {
        seckillSessionService.updateById(seckillSession);
        return MyResult.success();
    }


}
