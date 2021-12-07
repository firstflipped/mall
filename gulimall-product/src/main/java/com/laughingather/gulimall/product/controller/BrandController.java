package com.laughingather.gulimall.product.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.common.valid.AddGroup;
import com.laughingather.gulimall.common.valid.UpdateGroup;
import com.laughingather.gulimall.product.entity.BrandEntity;
import com.laughingather.gulimall.product.entity.query.BrandQuery;
import com.laughingather.gulimall.product.service.BrandService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


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
    @Resource
    private BrandService brandService;

    @GetMapping("/page")
    public MyResult<MyPage<BrandEntity>> pageBrandsByParams(@ModelAttribute BrandQuery brandQuery) {
        MyPage<BrandEntity> pageBrands = brandService.pageBrandsByParams(brandQuery);

        return MyResult.success(pageBrands);
    }

    @GetMapping("/{brandId}")
    public MyResult<BrandEntity> getBrandById(@PathVariable("brandId") Long brandId) {
        BrandEntity brand = brandService.getById(brandId);

        return brand == null ? MyResult.failed() : MyResult.success(brand);
    }

    @GetMapping("/list")
    public MyResult<List<BrandEntity>> listBrandsByIds(@RequestParam(value = "brandIds", required = false) List<Long> brandIds) {
        List<BrandEntity> brands;
        if (CollectionUtils.isNotEmpty(brandIds)) {
            brands = brandService.listByIds(brandIds);
        } else {
            brands = brandService.list();
        }
        return MyResult.success(brands);
    }

    @PostMapping
    public MyResult saveBrand(@Validated({AddGroup.class}) @RequestBody BrandEntity brand) {
        brandService.save(brand);
        return MyResult.success();
    }

    @DeleteMapping("/{brandId}")
    public MyResult deleteBrandById(@PathVariable("brandId") Long brandId) {
        boolean isSuccess = brandService.removeById(brandId);
        return isSuccess ? MyResult.success() : MyResult.failed();
    }

    @DeleteMapping
    public MyResult deleteBrandsByIds(@RequestBody Long[] brandIds) {
        boolean isSuccess = brandService.removeByIds(Arrays.asList(brandIds));
        return isSuccess ? MyResult.success() : MyResult.failed();
    }

    @PutMapping
    public MyResult updateBrandById(@Validated({UpdateGroup.class}) @RequestBody BrandEntity brand) {
        boolean isSuccess = brandService.updateBrandById(brand);
        return isSuccess ? MyResult.success() : MyResult.failed();
    }


}
