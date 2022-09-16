package com.laughingather.gulimall.product.controller;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.common.valid.AddGroup;
import com.laughingather.gulimall.product.entity.BrandEntity;
import com.laughingather.gulimall.product.entity.CategoryBrandRelationEntity;
import com.laughingather.gulimall.product.entity.CategoryEntity;
import com.laughingather.gulimall.product.entity.param.CategoryBrandRelationParam;
import com.laughingather.gulimall.product.service.CategoryBrandRelationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 品牌&分类关联路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/product/category-brand-relation")
@Tag(name = "品牌&分类关联模块")
public class CategoryBrandRelationController {

    @Resource
    private CategoryBrandRelationService categoryBrandRelationService;

    /**
     * 获取当前品牌关联的所有分类列表
     *
     * @param brandId
     * @return
     */
    @GetMapping("/list/category")
    @Operation(summary = "根据品牌id获取分类列表")
    public MyResult<List<CategoryEntity>> listCategoryByBrandId(@RequestParam("bid") Long brandId) {
        List<CategoryEntity> categories = categoryBrandRelationService.listCategoryByBrandId(brandId);
        return MyResult.success(categories);
    }

    @GetMapping("/list/brand")
    @Operation(summary = "根据分类id获取品牌列表")
    public MyResult<List<BrandEntity>> listBrandsByCategoryId(@RequestParam("cid") Long categoryId) {
        List<BrandEntity> brands = categoryBrandRelationService.listBrandsByCategoryId(categoryId);
        return MyResult.success(brands);
    }

    @PostMapping
    @Operation(summary = "保存分类&品牌关联关系信息")
    public MyResult<Void> saveCategoryBrandRelation(@Validated(value = {AddGroup.class}) @RequestBody
                                                            CategoryBrandRelationParam categoryBrandRelationParam) {
        CategoryBrandRelationEntity categoryBrandRelation = new CategoryBrandRelationEntity();
        BeanUtils.copyProperties(categoryBrandRelationParam, categoryBrandRelation);

        categoryBrandRelationService.saveCategoryBrandRelation(categoryBrandRelation);
        return MyResult.success();
    }

}
