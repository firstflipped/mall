package com.laughingather.gulimall.product.controller;

import com.laughingather.gulimall.common.api.Result;
import com.laughingather.gulimall.product.entity.CategoryEntity;
import com.laughingather.gulimall.product.entity.vo.CategoryTreeVO;
import com.laughingather.gulimall.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


/**
 * 商品三级分类
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
@RestController
@RequestMapping("/product/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 查询分类列表，并组装成树
     */
    @GetMapping("/tree")
    public Result treeCategory() {
        List<CategoryTreeVO> categoryTreeVOs = categoryService.listWithTree();
        return Result.success(categoryTreeVOs);
    }

    @GetMapping("/get/{catId}")
    public Result getCategoryById(@PathVariable("catId") Long catId) {
        CategoryEntity category = categoryService.getById(catId);
        return category == null ? Result.failed() : Result.success(category);
    }

    @PostMapping("/save")
    public Result saveCategory(@RequestBody CategoryEntity category) {
        boolean isSuccess = categoryService.save(category);
        return isSuccess ? Result.success(category) : Result.failed();
    }

    @DeleteMapping("/delete")
    public Result deleteCategoryByIds(@RequestBody Long[] catIds) {
        List<Long> catIdList = Arrays.asList(catIds);
        boolean isSuccess = categoryService.deleteCategoryByIds(catIdList);

        return isSuccess ? Result.success(catIdList) : Result.failed();
    }

    @PutMapping("/update")
    public Result updateCategoryById(@RequestBody CategoryEntity category) {
        boolean isSuccess = categoryService.updateById(category);
        return isSuccess ? Result.success(null) : Result.failed();
    }

    @PutMapping("/update/dragAndDrop")
    public Result updateDragAndDrop(@RequestBody CategoryEntity[] categories) {
        boolean isSuccess = categoryService.updateBatchById(Arrays.asList(categories));
        return isSuccess ? Result.success(null) : Result.failed();
    }
}
