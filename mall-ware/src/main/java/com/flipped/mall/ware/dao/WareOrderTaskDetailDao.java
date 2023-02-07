package com.flipped.mall.ware.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flipped.mall.ware.entity.WareOrderTaskDetailEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 库存工作单
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Mapper
public interface WareOrderTaskDetailDao extends BaseMapper<WareOrderTaskDetailEntity> {

    /**
     * 更新库存锁定工作单详情状态
     *
     * @param detailId
     * @param status
     */
    void updateStatusById(@Param("detailId") Long detailId, @Param("status") Integer status);
}
