package com.flipped.mall.product.controller;

import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.product.entity.vo.CategoryTreeVO;
import com.flipped.mall.product.service.CategoryService;
import com.flipped.mall.product.entity.CategoryEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "分类管理")
public class CategoryController {

    @Resource
    private CategoryService categoryService;


    @GetMapping("/tree")
    @Operation(summary = "树形结构查询分类列表")
    public MyResult<List<CategoryTreeVO>> listCategoryWithTree() {
        List<CategoryTreeVO> categoryTreeVOList = categoryService.listWithTree();
        return MyResult.success(categoryTreeVOList);
    }

    @GetMapping("/{cid}")
    @Operation(summary = "查询分类详情信息")
    public MyResult<CategoryEntity> getCategoryById(@PathVariable("cid") Long categoryId) {
        CategoryEntity category = categoryService.getById(categoryId);
        return category == null ? MyResult.failed() : MyResult.success(category);
    }

    @PostMapping
    @Operation(summary = "保存分类信息")
    public MyResult<CategoryEntity> saveCategory(@RequestBody CategoryEntity category) {
        // 预设置创建时间
        category.setCreateTime(LocalDateTime.now());

        boolean isSuccess = categoryService.save(category);
        return isSuccess ? MyResult.success() : MyResult.failed();
    }

    @DeleteMapping
    @Operation(summary = "批量删除分类信息")
    public MyResult<Void> deleteBatchCategoryByIds(@RequestBody Long[] categoryIds) {
        categoryService.deleteCategoryByIds(Arrays.asList(categoryIds));
        return MyResult.success();
    }


    @PutMapping
    @CacheEvict(value = "category", key = "'level1Category'")
    @Operation(summary = "更新分类信息", description = "采用了缓存失效模式，如果执行了该更新操作，则会把缓存删掉")
    public MyResult<Void> updateCategoryById(@RequestBody CategoryEntity category) {
        // 预设置更新时间
        category.setUpdateTime(LocalDateTime.now());

        boolean isSuccess = categoryService.updateById(category);
        return isSuccess ? MyResult.success() : MyResult.failed();
    }


    @PutMapping("/drag")
    @Operation(summary = "批量拖拽更新分类信息")
    public MyResult<Void> updateWithDrag(@RequestBody CategoryEntity[] categories) {
        // 预设置更新时间
        List<CategoryEntity> categoryList = Arrays.asList(categories);
        categoryList.forEach(item -> item.setUpdateTime(LocalDateTime.now()));

        boolean isSuccess = categoryService.updateBatchById(categoryList);
        return isSuccess ? MyResult.success() : MyResult.failed();
    }
}