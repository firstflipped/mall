package com.laughingather.gulimall.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.coupon.dao.SeckillSessionDao;
import com.laughingather.gulimall.coupon.entity.SeckillSessionEntity;
import com.laughingather.gulimall.coupon.entity.query.SeckillSessionQuery;
import com.laughingather.gulimall.coupon.entity.to.SeckillSessionTO;
import com.laughingather.gulimall.coupon.entity.to.SeckillSkuRelationTO;
import com.laughingather.gulimall.coupon.service.SeckillSessionService;
import com.laughingather.gulimall.coupon.service.SeckillSkuRelationService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 秒杀活动场次逻辑实现
 *
 * @author laughingather
 */
@Service("seckillSessionService")
public class SeckillSessionServiceImpl extends ServiceImpl<SeckillSessionDao, SeckillSessionEntity> implements SeckillSessionService {

    @Resource
    private SeckillSessionDao seckillSessionDao;
    @Resource
    private SeckillSkuRelationService seckillSkuRelationService;

    @Override
    public MyPage<SeckillSessionEntity> pageSeckillSession(SeckillSessionQuery seckillSessionQuery) {
        IPage<SeckillSessionEntity> page = new Page<>(seckillSessionQuery.getPageNumber(), seckillSessionQuery.getPageSize());
        IPage<SeckillSessionEntity> seckillSessionPage = baseMapper.selectPage(page, null);

        return MyPage.restPage(seckillSessionPage);
    }


    @Override
    public List<SeckillSessionTO> getLast3DaysSession() {
        // 拼接日期区间（最近三天内）
        LocalDate now = LocalDate.now();
        LocalDate twoDayLater = now.plusDays(2);
        String startTime = LocalDateTime.of(now, LocalTime.MIN).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String endTime = LocalDateTime.of(twoDayLater, LocalTime.MAX).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        List<SeckillSessionEntity> seckillSessions = seckillSessionDao.selectList(new QueryWrapper<SeckillSessionEntity>().lambda()
                .between(SeckillSessionEntity::getStartTime, startTime, endTime));

        List<SeckillSessionTO> seckillSessionTOList = seckillSessions.stream().map(seckillSession -> {
            SeckillSessionTO seckillSessionTO = new SeckillSessionTO();
            BeanUtils.copyProperties(seckillSession, seckillSessionTO);
            // 根据场次id查询该场次下的所有关联商品
            List<SeckillSkuRelationTO> seckillSkuRelationTO = seckillSkuRelationService.getRelationSkusByPromotionSessionId(seckillSession.getId());
            seckillSessionTO.setSkuRelations(seckillSkuRelationTO);
            return seckillSessionTO;
        }).collect(Collectors.toList());

        return seckillSessionTOList;
    }
}