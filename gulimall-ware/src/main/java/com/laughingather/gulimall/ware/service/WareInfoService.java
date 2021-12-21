package com.laughingather.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.ware.entity.WareInfoEntity;
import com.laughingather.gulimall.ware.entity.query.WareInfoQuery;
import com.laughingather.gulimall.ware.entity.vo.FareVO;

/**
 * 仓库信息
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:57:23
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

