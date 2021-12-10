package com.laughingather.gulimall.coupon.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.coupon.entity.SecKillSessionEntity;
import com.laughingather.gulimall.coupon.entity.query.SecKillSessionQuery;
import com.laughingather.gulimall.coupon.service.SecKillSessionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;


/**
 * 秒杀活动场次
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:49:52
 */
@RestController
@RequestMapping("/coupon/sec-kill-session")
public class SecKillSessionController {
    @Resource
    private SecKillSessionService secKillSessionService;

    @GetMapping("/page")
    public MyResult<MyPage<SecKillSessionEntity>> pageSecKillSession(@ModelAttribute SecKillSessionQuery secKillSessionQuery) {
        MyPage<SecKillSessionEntity> secKillSessionPage = secKillSessionService.pageSecKillSession(secKillSessionQuery);
        return MyResult.success(secKillSessionPage);
    }

    @GetMapping("/info/{id}")
    public MyResult<SecKillSessionEntity> getSecKillSessionById(@PathVariable Long id) {
        SecKillSessionEntity secKillSession = secKillSessionService.getById(id);
        return MyResult.success(secKillSession);
    }

    @PostMapping
    public MyResult saveSecKillSession(@RequestBody SecKillSessionEntity secKillSession) {
        secKillSession.setCreateTime(LocalDateTime.now());
        secKillSessionService.save(secKillSession);
        return MyResult.success();
    }

    @PutMapping
    public MyResult updateSecKillSessionById(@RequestBody SecKillSessionEntity secKillSession) {
        secKillSessionService.updateById(secKillSession);
        return MyResult.success();
    }


}
