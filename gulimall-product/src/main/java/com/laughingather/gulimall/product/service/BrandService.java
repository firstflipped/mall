package com.laughingather.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.product.entity.BrandEntity;
import com.laughingather.gulimall.product.entity.query.BrandQuery;
import com.laughingather.gulimall.product.entity.vo.BrandSelectVO;

import java.util.List;

/**
 * 品牌管理逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
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

    /**
     * 根据品牌id查询品牌名称
     *
     * @param brandId 品牌id
     * @return 品牌名称
     */
    String getBrandNameByBrandId(Long brandId);

    /**
     * 查询品牌列表（仅供前端下拉选择器使用）
     *
     * @param brandName 品牌名称
     * @return 品牌列表
     */
    List<BrandSelectVO> listBrandsWithSelect(String brandName);
}

