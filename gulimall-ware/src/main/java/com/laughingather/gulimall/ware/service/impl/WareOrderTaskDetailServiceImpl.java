package com.laughingather.gulimall.ware.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.ware.dao.WareOrderTaskDetailDao;
import com.laughingather.gulimall.ware.entity.WareOrderTaskDetailEntity;
import com.laughingather.gulimall.ware.service.WareOrderTaskDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 库存锁定工作单详情逻辑层
 *
 * @author laughingather
 */
@Service("wareOrderTaskDetailService")
public class WareOrderTaskDetailServiceImpl extends ServiceImpl<WareOrderTaskDetailDao, WareOrderTaskDetailEntity> implements WareOrderTaskDetailService {

    @Autowired
    private WareOrderTaskDetailDao wareOrderTaskDetailDao;

    @Override
    public void updateStatusById(Long detailId, Integer status) {
        wareOrderTaskDetailDao.updateStatusById(detailId, status);
    }
}