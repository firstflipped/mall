package com.laughingather.gulimall.product.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.laughingather.gulimall.common.api.Result;
import com.laughingather.gulimall.common.valid.AddGroup;
import com.laughingather.gulimall.common.valid.UpdateGroup;
import com.laughingather.gulimall.product.entity.BrandEntity;
import com.laughingather.gulimall.product.entity.query.BrandQuery;
import com.laughingather.gulimall.product.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * 品牌
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping("/page")
    public Result pageBrandsByParams(@ModelAttribute BrandQuery brandQuery) {
        IPage<BrandEntity> pageBrands = brandService.pageBrandsByParams(brandQuery);

        return Result.success(pageBrands);
    }

    @GetMapping("/{brandId}")
    public Result getBrandById(@PathVariable("brandId") Long brandId) {
        BrandEntity brand = brandService.getById(brandId);

        return brand == null ? Result.failed() : Result.success(brand);
    }

    @PostMapping
    public Result saveBrand(@Validated({AddGroup.class}) @RequestBody BrandEntity brand) {
        brandService.save(brand);
        return Result.success(brand);
    }

    @DeleteMapping("/{brandId}")
    public Result deleteBrandById(@PathVariable("brandId") Long brandId) {
        boolean isSuccess = brandService.removeById(brandId);

        return isSuccess ? Result.success(brandId) : Result.failed();
    }

    @DeleteMapping
    public Result deleteBrandsByIds(@RequestBody Long[] brandIds) {
        boolean isSuccess = brandService.removeByIds(Arrays.asList(brandIds));

        return isSuccess ? Result.success(brandIds) : Result.failed();
    }

    @PutMapping
    public Result updateBrandById(@Validated({UpdateGroup.class}) @RequestBody BrandEntity brand) {
        boolean isSuccess = brandService.updateById(brand);

        return isSuccess ? Result.success(brand) : Result.failed();
    }


}
