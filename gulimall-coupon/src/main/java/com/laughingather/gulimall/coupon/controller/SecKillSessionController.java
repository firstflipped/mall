package com.laughingather.gulimall.coupon.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.coupon.entity.SecKillSessionEntity;
import com.laughingather.gulimall.coupon.entity.query.SecKillSessionQuery;
import com.laughingather.gulimall.coupon.service.SecKillSessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "秒杀活动场次模块")
public class SecKillSessionController {
    @Resource
    private SecKillSessionService secKillSessionService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询秒杀活动场次列表")
    public MyResult<MyPage<SecKillSessionEntity>> pageSecKillSession(@ModelAttribute SecKillSessionQuery secKillSessionQuery) {
        MyPage<SecKillSessionEntity> secKillSessionPage = secKillSessionService.pageSecKillSession(secKillSessionQuery);
        return MyResult.success(secKillSessionPage);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询秒杀活动场次详情")
    public MyResult<SecKillSessionEntity> getSecKillSessionById(@PathVariable Long id) {
        SecKillSessionEntity secKillSession = secKillSessionService.getById(id);
        return MyResult.success(secKillSession);
    }

    @PostMapping
    @ApiOperation(value = "保存秒杀活动场次信息")
    public MyResult<Void> saveSecKillSession(@RequestBody SecKillSessionEntity secKillSession) {
        secKillSession.setCreateTime(LocalDateTime.now());
        secKillSessionService.save(secKillSession);
        return MyResult.success();
    }

    @PutMapping
    @ApiOperation(value = "更新秒杀活动场次信息")
    public MyResult<Void> updateSecKillSessionById(@RequestBody SecKillSessionEntity secKillSession) {
        secKillSessionService.updateById(secKillSession);
        return MyResult.success();
    }


}
