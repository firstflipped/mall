package com.laughingather.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.product.entity.BrandEntity;
import com.laughingather.gulimall.product.entity.query.BrandQuery;

/**
 * 品牌管理逻辑接口
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
public interface BrandService extends IService<BrandEntity> {

    /**
     * 分页查询品牌列表
     *
     * @param brandQuery 品牌列表查询条件
     * @return 品牌列表
     */
    MyPage<BrandEntity> listBrandsWithPage(BrandQuery brandQuery);

    /**
     * 保存品牌信息
     *
     * @param brand 新增品牌信息
     */
    void saveBrand(BrandEntity brand);

    /**
     * 更新品牌信息
     *
     * @param brand 更新品牌信息
     */
    void updateBrandById(BrandEntity brand);
}

