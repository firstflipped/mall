package com.flipped.mall.product.controller;

import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.common.valid.AddGroup;
import com.flipped.mall.common.valid.UpdateGroup;
import com.flipped.mall.product.entity.BrandEntity;
import com.flipped.mall.product.entity.query.BrandQuery;
import com.flipped.mall.product.entity.vo.BrandSelectVO;
import com.flipped.mall.product.service.BrandService;
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
public class BrandController {

    @Resource
    private BrandService brandService;

    @GetMapping("/page")
    public MyResult<MyPage<BrandEntity>> listBrandsWithPage(@Valid @ModelAttribute BrandQuery brandQuery) {
        MyPage<BrandEntity> pageBrands = brandService.listBrandsWithPage(brandQuery);

        return MyResult.success(pageBrands);
    }

    @GetMapping("/list")
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
    public MyResult<List<BrandSelectVO>> listBrandsWithSelect(@RequestParam(value = "name", required = false) String brandName) {
        List<BrandSelectVO> brands = brandService.listBrandsWithSelect(brandName);
        return MyResult.success(brands);
    }


    @GetMapping("/{bid}")
    public MyResult<BrandEntity> getBrandById(@PathVariable("bid") Long brandId) {
        BrandEntity brand = brandService.getById(brandId);

        return brand == null ? MyResult.failed() : MyResult.success(brand);
    }

    @PostMapping
    public MyResult<Void> saveBrand(@Validated({AddGroup.class}) @RequestBody BrandEntity brand) {
        brandService.saveBrand(brand);
        return MyResult.success();
    }

    @DeleteMapping("/{bid}")
    public MyResult<Void> deleteBrandById(@PathVariable("bid") Long brandId) {
        boolean isSuccess = brandService.removeById(brandId);
        return isSuccess ? MyResult.success() : MyResult.failed();
    }

    @DeleteMapping
    public MyResult<Void> deleteBatchBrandsByIds(@RequestBody Long[] brandIds) {
        boolean isSuccess = brandService.removeByIds(Arrays.asList(brandIds));
        return isSuccess ? MyResult.success() : MyResult.failed();
    }

    @PutMapping
    public MyResult<Void> updateBrandById(@Validated({UpdateGroup.class}) @RequestBody BrandEntity brand) {
        brandService.updateBrandById(brand);
        return MyResult.success();
    }

}
