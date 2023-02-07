package com.flipped.mall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flipped.mall.common.constant.WareConstants;
import com.flipped.mall.ware.dao.WareOrderTaskDetailDao;
import com.flipped.mall.ware.entity.WareOrderTaskDetailEntity;
import com.flipped.mall.ware.service.WareOrderTaskDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 库存锁定工作单详情逻辑层
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
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