package com.laughingather.gulimall.product.controller;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.product.entity.CategoryEntity;
import com.laughingather.gulimall.product.entity.vo.CategoryTreeVO;
import com.laughingather.gulimall.product.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


/**
 * 商品分类路由
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
@RestController
@RequestMapping("/product/category")
@ApiOperation("分类模块")
public class CategoryController {

    @Resource
    private CategoryService categoryService;


    @GetMapping("/tree")
    @ApiOperation("树形结构查询分类列表")
    public MyResult<List<CategoryTreeVO>> listCategoryWithTree() {
        List<CategoryTreeVO> categoryTreeVOs = categoryService.listWithTree();
        return MyResult.success(categoryTreeVOs);
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
        boolean isSuccess = categoryService.save(category);
        return isSuccess ? MyResult.success() : MyResult.failed();
    }

    @DeleteMapping
    @ApiOperation(value = "批量删除分类信息")
    public MyResult deleteBatchCategoryByIds(@RequestBody Long[] categoryIds) {
        categoryService.deleteCategoryByIds(Arrays.asList(categoryIds));

        return MyResult.success();
    }

    /**
     * @param category
     * @return
     * @CacheEvict：缓存失效模式，更新数据后会自动把缓存删掉
     */
    @PutMapping
    @CacheEvict(value = "category", key = "'level1Category'")
    @ApiOperation(value = "更新分类信息", notes = "采用了缓存失效模式，如果执行了该更新操作，则会把缓存删掉")
    public MyResult updateCategoryById(@RequestBody CategoryEntity category) {
        boolean isSuccess = categoryService.updateById(category);
        return isSuccess ? MyResult.success() : MyResult.failed();
    }


    @PutMapping("/drag")
    @ApiOperation(value = "批量拖拽更新分类信息")
    public MyResult updateWithDrag(@RequestBody CategoryEntity[] categories) {
        boolean isSuccess = categoryService.updateBatchById(Arrays.asList(categories));
        return isSuccess ? MyResult.success() : MyResult.failed();
    }
}
