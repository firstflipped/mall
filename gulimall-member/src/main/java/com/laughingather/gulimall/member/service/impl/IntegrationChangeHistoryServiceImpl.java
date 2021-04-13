package com.laughingather.gulimall.member.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.laughingather.gulimall.member.dao.IntegrationChangeHistoryDao;
import com.laughingather.gulimall.member.entity.IntegrationChangeHistoryEntity;
import com.laughingather.gulimall.member.service.IntegrationChangeHistoryService;


@Service("integrationChangeHistoryService")
public class IntegrationChangeHistoryServiceImpl extends ServiceImpl<IntegrationChangeHistoryDao, IntegrationChangeHistoryEntity> implements IntegrationChangeHistoryService {

}