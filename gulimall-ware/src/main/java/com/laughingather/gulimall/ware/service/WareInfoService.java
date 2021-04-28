package com.laughingather.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.ware.entity.WareInfoEntity;
import com.laughingather.gulimall.ware.entity.query.WareInfoQuery;

/**
 * 仓库信息
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:57:23
 */
public interface WareInfoService extends IService<WareInfoEntity> {

    MyPage<WareInfoEntity> pageWareInfoByParams(WareInfoQuery wareInfoQuery);
}

