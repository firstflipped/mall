package com.flipped.mall.product.controller;

import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.product.entity.CategoryEntity;
import com.flipped.mall.product.entity.vo.CategoryTreeVO;
import com.flipped.mall.product.service.CategoryService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


/**
 * 分类管理路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/product/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/tree")
    public MyResult<List<CategoryTreeVO>> listCategoryWithTree() {
        List<CategoryTreeVO> categoryTreeVOList = categoryService.listWithTree();
        return MyResult.success(categoryTreeVOList);
    }

    @GetMapping("/{cid}")
    public MyResult<CategoryEntity> getCategoryById(@PathVariable("cid") Long categoryId) {
        CategoryEntity category = categoryService.getById(categoryId);
        return category == null ? MyResult.failed() : MyResult.success(category);
    }

    @PostMapping
    public MyResult<CategoryEntity> saveCategory(@RequestBody CategoryEntity category) {
        // 预设置创建时间
        category.setCreateTime(LocalDateTime.now());

        boolean isSuccess = categoryService.save(category);
        return isSuccess ? MyResult.success() : MyResult.failed();
    }

    @DeleteMapping
    public MyResult<Void> deleteBatchCategoryByIds(@RequestBody Long[] categoryIds) {
        categoryService.deleteCategoryByIds(Arrays.asList(categoryIds));
        return MyResult.success();
    }


    @PutMapping
    @CacheEvict(value = "category", key = "'level1Category'")
    public MyResult<Void> updateCategoryById(@RequestBody CategoryEntity category) {
        // 预设置更新时间
        category.setUpdateTime(LocalDateTime.now());

        boolean isSuccess = categoryService.updateById(category);
        return isSuccess ? MyResult.success() : MyResult.failed();
    }


    @PutMapping("/drag")
    public MyResult<Void> updateWithDrag(@RequestBody CategoryEntity[] categories) {
        // 预设置更新时间
        List<CategoryEntity> categoryList = Arrays.asList(categories);
        categoryList.forEach(item -> item.setUpdateTime(LocalDateTime.now()));

        boolean isSuccess = categoryService.updateBatchById(categoryList);
        return isSuccess ? MyResult.success() : MyResult.failed();
    }
}
