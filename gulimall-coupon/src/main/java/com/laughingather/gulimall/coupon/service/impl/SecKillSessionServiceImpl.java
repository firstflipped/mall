package com.laughingather.gulimall.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.coupon.dao.SecKillSessionDao;
import com.laughingather.gulimall.coupon.entity.SecKillSessionEntity;
import com.laughingather.gulimall.coupon.entity.query.SecKillSessionQuery;
import com.laughingather.gulimall.coupon.entity.dto.SecKillSessionDTO;
import com.laughingather.gulimall.coupon.entity.dto.SecKillSkuRelationDTO;
import com.laughingather.gulimall.coupon.service.SecKillSessionService;
import com.laughingather.gulimall.coupon.service.SecKillSkuRelationService;
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
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Service("secKillSessionService")
public class SecKillSessionServiceImpl extends ServiceImpl<SecKillSessionDao, SecKillSessionEntity> implements SecKillSessionService {

    @Resource
    private SecKillSessionDao secKillSessionDao;
    @Resource
    private SecKillSkuRelationService secKillSkuRelationService;

    @Override
    public MyPage<SecKillSessionEntity> pageSecKillSession(SecKillSessionQuery seckillSessionQuery) {
        IPage<SecKillSessionEntity> page = new Page<>(seckillSessionQuery.getPn(), seckillSessionQuery.getPs());
        IPage<SecKillSessionEntity> secKillSessionPage = baseMapper.selectPage(page, null);

        return MyPage.restPage(secKillSessionPage);
    }


    @Override
    public List<SecKillSessionDTO> getLast3DaysSession() {
        // 拼接日期区间（最近三天内）
        LocalDate now = LocalDate.now();
        LocalDate twoDayLater = now.plusDays(2);
        String startTime = LocalDateTime.of(now, LocalTime.MIN).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String endTime = LocalDateTime.of(twoDayLater, LocalTime.MAX).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        List<SecKillSessionEntity> secKillSessions = secKillSessionDao.selectList(new QueryWrapper<SecKillSessionEntity>().lambda()
                .between(SecKillSessionEntity::getStartTime, startTime, endTime));

        List<SecKillSessionDTO> secKillSessionTOList = secKillSessions.stream().map(secKillSession -> {
            SecKillSessionDTO secKillSessionTO = new SecKillSessionDTO();
            BeanUtils.copyProperties(secKillSession, secKillSessionTO);
            // 根据场次id查询该场次下的所有关联商品
            List<SecKillSkuRelationDTO> secKillSkuRelationTO = secKillSkuRelationService.getRelationSkusByPromotionSessionId(secKillSession.getId());
            secKillSessionTO.setSkuRelations(secKillSkuRelationTO);
            return secKillSessionTO;
        }).collect(Collectors.toList());

        return secKillSessionTOList;
    }
}