package com.laughingather.gulimall.product.controller;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.product.entity.CategoryEntity;
import com.laughingather.gulimall.product.entity.vo.CategoryTreeVO;
import com.laughingather.gulimall.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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
    public MyResult<List<CategoryTreeVO>> treeCategory() {
        List<CategoryTreeVO> categoryTreeVOs = categoryService.listWithTree();
        return MyResult.success(categoryTreeVOs);
    }

    @GetMapping("/{catId}")
    public MyResult<CategoryEntity> getCategoryById(@PathVariable("catId") Long catId) {
        CategoryEntity category = categoryService.getById(catId);
        return category == null ? MyResult.failed() : MyResult.success(category);
    }

    @PostMapping("/save")
    public MyResult<CategoryEntity> saveCategory(@RequestBody CategoryEntity category) {
        boolean isSuccess = categoryService.save(category);
        return isSuccess ? MyResult.success() : MyResult.failed();
    }

    @DeleteMapping("/delete")
    public MyResult deleteCategoryByIds(@RequestBody Long[] catIds) {
        List<Long> catIdList = Arrays.asList(catIds);
        boolean isSuccess = categoryService.deleteCategoryByIds(catIdList);

        return isSuccess ? MyResult.success() : MyResult.failed();
    }

    /**
     * @param category
     * @return
     * @CacheEvict：缓存失效模式，更新数据后会自动把缓存删掉
     */
    @PutMapping("/update")
    @CacheEvict(value = "category", key = "'level1Categorys'")
    public MyResult updateCategoryById(@RequestBody CategoryEntity category) {
        boolean isSuccess = categoryService.updateById(category);
        return isSuccess ? MyResult.success() : MyResult.failed();
    }

    @PutMapping("/update/dragAndDrop")
    public MyResult updateDragAndDrop(@RequestBody CategoryEntity[] categories) {
        boolean isSuccess = categoryService.updateBatchById(Arrays.asList(categories));
        return isSuccess ? MyResult.success() : MyResult.failed();
    }
}
