package com.laughingather.gulimall.product.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.common.valid.AddGroup;
import com.laughingather.gulimall.common.valid.UpdateGroup;
import com.laughingather.gulimall.product.entity.BrandEntity;
import com.laughingather.gulimall.product.entity.query.BrandQuery;
import com.laughingather.gulimall.product.entity.vo.BrandSelectVO;
import com.laughingather.gulimall.product.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;


/**
 * 品牌管理路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/product/brand")
@Tag(name = "品牌管理")
public class BrandController {

    @Resource
    private BrandService brandService;

    @GetMapping("/page")
    @Operation(summary = "分页查询品牌列表")
    public MyResult<MyPage<BrandEntity>> listBrandsWithPage(@Valid @ModelAttribute BrandQuery brandQuery) {
        MyPage<BrandEntity> pageBrands = brandService.listBrandsWithPage(brandQuery);

        return MyResult.success(pageBrands);
    }

    @GetMapping("/list")
    @Operation(summary = "查询品牌列表")
    @Parameter(name = "bids", description = "品牌id集合")
    public MyResult<List<BrandEntity>> listBrands(@RequestParam(value = "bids", required = false) List<Long> brandIds) {
        List<BrandEntity> brands;
        if (CollectionUtils.isNotEmpty(brandIds)) {
            brands = brandService.listByIds(brandIds);
        } else {
            brands = brandService.list();
        }

        return MyResult.success(brands);
    }


    @GetMapping("/list/select")
    @Operation(summary = "查询品牌列表（仅供前端下拉选择器使用）")
    @Parameter(name = "name", description = "品牌名称")
    public MyResult<List<BrandSelectVO>> listBrandsWithSelect(@RequestParam(value = "name", required = false) String brandName) {
        List<BrandSelectVO> brands = brandService.listBrandsWithSelect(brandName);
        return MyResult.success(brands);
    }


    @GetMapping("/{bid}")
    @Operation(summary = "查询品牌详情信息")
    public MyResult<BrandEntity> getBrandById(@PathVariable("bid") Long brandId) {
        BrandEntity brand = brandService.getById(brandId);

        return brand == null ? MyResult.failed() : MyResult.success(brand);
    }

    @PostMapping
    @Operation(summary = "保存品牌信息")
    public MyResult<Void> saveBrand(@Validated({AddGroup.class}) @RequestBody BrandEntity brand) {
        brandService.saveBrand(brand);
        return MyResult.success();
    }

    @DeleteMapping("/{bid}")
    @Operation(summary = "删除品牌信息")
    public MyResult<Void> deleteBrandById(@PathVariable("bid") Long brandId) {
        boolean isSuccess = brandService.removeById(brandId);
        return isSuccess ? MyResult.success() : MyResult.failed();
    }

    @DeleteMapping
    @Operation(summary = "批量删除品牌信息")
    public MyResult<Void> deleteBatchBrandsByIds(@RequestBody Long[] brandIds) {
        boolean isSuccess = brandService.removeByIds(Arrays.asList(brandIds));
        return isSuccess ? MyResult.success() : MyResult.failed();
    }

    @PutMapping
    @Operation(summary = "更新品牌信息")
    public MyResult<Void> updateBrandById(@Validated({UpdateGroup.class}) @RequestBody BrandEntity brand) {
        brandService.updateBrandById(brand);
        return MyResult.success();
    }


}
