package com.laughingather.gulimall.product.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.common.valid.AddGroup;
import com.laughingather.gulimall.common.valid.UpdateGroup;
import com.laughingather.gulimall.product.entity.BrandEntity;
import com.laughingather.gulimall.product.entity.query.BrandQuery;
import com.laughingather.gulimall.product.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


/**
 * 品牌管理路由
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
@RestController
@RequestMapping("/product/brand")
@Api(tags = "品牌管理")
public class BrandController {

    @Resource
    private BrandService brandService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询品牌列表")
    public MyResult<MyPage<BrandEntity>> listBrandsWithPage(@ModelAttribute BrandQuery brandQuery) {
        MyPage<BrandEntity> pageBrands = brandService.listBrandsWithPage(brandQuery);

        return MyResult.success(pageBrands);
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询品牌列表")
    @ApiImplicitParam(name = "bids", value = "品牌id集合", dataTypeClass = List.class)
    public MyResult<List<BrandEntity>> listBrands(@RequestParam(value = "bids", required = false) List<Long> brandIds) {
        List<BrandEntity> brands;
        if (CollectionUtils.isNotEmpty(brandIds)) {
            brands = brandService.listByIds(brandIds);
        } else {
            brands = brandService.list();
        }

        return MyResult.success(brands);
    }

    @GetMapping("/{bid}")
    @ApiOperation(value = "查询品牌详情信息")
    public MyResult<BrandEntity> getBrandById(@PathVariable("bid") Long brandId) {
        BrandEntity brand = brandService.getById(brandId);

        return brand == null ? MyResult.failed() : MyResult.success(brand);
    }

    @PostMapping
    @ApiOperation(value = "保存品牌信息")
    public MyResult<Void> saveBrand(@Validated({AddGroup.class}) @RequestBody BrandEntity brand) {
        brandService.saveBrand(brand);
        return MyResult.success();
    }

    @DeleteMapping("/{bid}")
    @ApiOperation(value = "删除品牌信息")
    public MyResult<Void> deleteBrandById(@PathVariable("bid") Long brandId) {
        boolean isSuccess = brandService.removeById(brandId);
        return isSuccess ? MyResult.success() : MyResult.failed();
    }

    @DeleteMapping
    @ApiOperation(value = "批量删除品牌信息")
    public MyResult<Void> deleteBatchBrandsByIds(@RequestBody Long[] brandIds) {
        boolean isSuccess = brandService.removeByIds(Arrays.asList(brandIds));
        return isSuccess ? MyResult.success() : MyResult.failed();
    }

    @PutMapping
    @ApiOperation(value = "更新品牌信息")
    public MyResult<Void> updateBrandById(@Validated({UpdateGroup.class}) @RequestBody BrandEntity brand) {
        brandService.updateBrandById(brand);
        return MyResult.success();
    }


}
