package com.laughingather.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.ware.entity.WareInfoEntity;
import com.laughingather.gulimall.ware.entity.query.WareInfoQuery;
import com.laughingather.gulimall.ware.entity.vo.FareVO;

/**
 * 仓库信息
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface WareInfoService extends IService<WareInfoEntity> {

    /**
     * 分页查询
     *
     * @param wareInfoQuery
     * @return
     */
    MyPage<WareInfoEntity> listWaresWithPage(WareInfoQuery wareInfoQuery);

    /**
     * 获取运费
     *
     * @param addressId
     * @return
     */
    FareVO getFare(Long addressId);
}

