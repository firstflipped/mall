package com.flipped.mall.product.controller;

import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.common.valid.AddGroup;
import com.flipped.mall.product.entity.BrandEntity;
import com.flipped.mall.product.entity.CategoryBrandRelationEntity;
import com.flipped.mall.product.entity.CategoryEntity;
import com.flipped.mall.product.entity.param.CategoryBrandRelationParam;
import com.flipped.mall.product.service.CategoryBrandRelationService;
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
    public MyResult<List<CategoryEntity>> listCategoryByBrandId(@RequestParam("bid") Long brandId) {
        List<CategoryEntity> categories = categoryBrandRelationService.listCategoryByBrandId(brandId);
        return MyResult.success(categories);
    }

    @GetMapping("/list/brand")
    public MyResult<List<BrandEntity>> listBrandsByCategoryId(@RequestParam("cid") Long categoryId) {
        List<BrandEntity> brands = categoryBrandRelationService.listBrandsByCategoryId(categoryId);
        return MyResult.success(brands);
    }

    @PostMapping
    public MyResult<Void> saveCategoryBrandRelation(@Validated(value = {AddGroup.class}) @RequestBody
                                                    CategoryBrandRelationParam categoryBrandRelationParam) {
        CategoryBrandRelationEntity categoryBrandRelation = new CategoryBrandRelationEntity();
        BeanUtils.copyProperties(categoryBrandRelationParam, categoryBrandRelation);

        categoryBrandRelationService.saveCategoryBrandRelation(categoryBrandRelation);
        return MyResult.success();
    }

}
