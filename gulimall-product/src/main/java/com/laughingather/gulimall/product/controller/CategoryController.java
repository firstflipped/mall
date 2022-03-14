package com.laughingather.gulimall.product.controller;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.product.entity.CategoryEntity;
import com.laughingather.gulimall.product.entity.vo.CategoryTreeVO;
import com.laughingather.gulimall.product.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


/**
 * 分类管理路由
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
@RestController
@RequestMapping("/product/category")
@Api(tags = "分类管理")
public class CategoryController {

    @Resource
    private CategoryService categoryService;


    @GetMapping("/tree")
    @ApiOperation("树形结构查询分类列表")
    public MyResult<List<CategoryTreeVO>> listCategoryWithTree() {
        List<CategoryTreeVO> categoryTreeVOList = categoryService.listWithTree();
        return MyResult.success(categoryTreeVOList);
    }

    @GetMapping("/{cid}")
    @ApiOperation(value = "查询分类详情信息")
    public MyResult<CategoryEntity> getCategoryById(@PathVariable("cid") Long categoryId) {
        CategoryEntity category = categoryService.getById(categoryId);
        return category == null ? MyResult.failed() : MyResult.success(category);
    }

    @PostMapping
    @ApiOperation(value = "保存分类信息")
    public MyResult<CategoryEntity> saveCategory(@RequestBody CategoryEntity category) {
        // 预设置创建时间
        category.setCreateTime(LocalDateTime.now());

        boolean isSuccess = categoryService.save(category);
        return isSuccess ? MyResult.success() : MyResult.failed();
    }

    @DeleteMapping
    @ApiOperation(value = "批量删除分类信息")
    public MyResult<Void> deleteBatchCategoryByIds(@RequestBody Long[] categoryIds) {
        categoryService.deleteCategoryByIds(Arrays.asList(categoryIds));
        return MyResult.success();
    }


    @PutMapping
    @CacheEvict(value = "category", key = "'level1Category'")
    @ApiOperation(value = "更新分类信息", notes = "采用了缓存失效模式，如果执行了该更新操作，则会把缓存删掉")
    public MyResult<Void> updateCategoryById(@RequestBody CategoryEntity category) {
        // 预设置更新时间
        category.setUpdateTime(LocalDateTime.now());

        boolean isSuccess = categoryService.updateById(category);
        return isSuccess ? MyResult.success() : MyResult.failed();
    }


    @PutMapping("/drag")
    @ApiOperation(value = "批量拖拽更新分类信息")
    public MyResult<Void> updateWithDrag(@RequestBody CategoryEntity[] categories) {
        // 预设置更新时间
        List<CategoryEntity> categoryList = Arrays.asList(categories);
        categoryList.forEach(item -> item.setUpdateTime(LocalDateTime.now()));

        boolean isSuccess = categoryService.updateBatchById(categoryList);
        return isSuccess ? MyResult.success() : MyResult.failed();
    }
}
