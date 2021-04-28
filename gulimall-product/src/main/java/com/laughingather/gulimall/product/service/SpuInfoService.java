package com.laughingather.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.product.entity.SpuInfoEntity;
import com.laughingather.gulimall.product.entity.dto.SpuSaveDTO;
import com.laughingather.gulimall.product.entity.query.SpuInfoQuery;

/**
 * spu信息
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    void saveSpuInfo(SpuSaveDTO spuSaveDTO);

    MyPage<SpuInfoEntity> pageSpuInfoByParams(SpuInfoQuery spuInfoQuery);
}

