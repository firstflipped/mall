package com.laughingather.gulimall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.constant.WareConstants;
import com.laughingather.gulimall.ware.dao.WareOrderTaskDetailDao;
import com.laughingather.gulimall.ware.entity.WareOrderTaskDetailEntity;
import com.laughingather.gulimall.ware.service.WareOrderTaskDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 库存锁定工作单详情逻辑层
 *
 * @author laughingather
 */
@Service("wareOrderTaskDetailService")
public class WareOrderTaskDetailServiceImpl extends ServiceImpl<WareOrderTaskDetailDao, WareOrderTaskDetailEntity> implements WareOrderTaskDetailService {

    @Resource
    private WareOrderTaskDetailDao wareOrderTaskDetailDao;

    @Override
    public void updateStatusById(Long detailId, Integer status) {
        wareOrderTaskDetailDao.updateStatusById(detailId, status);
    }

    @Override
    public List<WareOrderTaskDetailEntity> listLockerWareOrderTaskDetailByTaskId(Long taskId) {
        QueryWrapper<WareOrderTaskDetailEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(WareOrderTaskDetailEntity::getTaskId, taskId)
                .eq(WareOrderTaskDetailEntity::getLockStatus, WareConstants.StockLockEnum.LOCKED.getCode());

        return this.list(queryWrapper);
    }
}