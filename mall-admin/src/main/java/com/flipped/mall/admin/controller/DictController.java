package com.flipped.mall.admin.controller;

import com.flipped.mall.admin.entity.DictEntity;
import com.flipped.mall.admin.entity.query.DictQuery;
import com.flipped.mall.admin.entity.vo.DictSelectVO;
import com.flipped.mall.admin.service.DictService;
import com.flipped.mall.common.annotation.PlatformLogAnnotation;
import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.common.valid.AddGroup;
import com.flipped.mall.common.valid.UpdateGroup;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-12-21 15:12:15
 */
@RestController
@RequestMapping("/admin/dict")
@Tag(name = "字典管理模块")
public class DictController {

    @Resource
    private DictService dictService;

    @GetMapping("/page")
    @PreAuthorize("hasAuthority('admin.dict.view')")
    @Operation(summary = "分页查询字典列表")
    @PlatformLogAnnotation(value = "分页查询字典列表")
    public MyResult<MyPage<DictEntity>> listDictWithPage(@ModelAttribute DictQuery dictQuery) {
        MyPage<DictEntity> dictWithPage = dictService.listDictWithPage(dictQuery);
        return MyResult.success(dictWithPage);
    }

    @GetMapping("/list/select")
    @Operation(summary = "查询字典列表（仅供前端下拉选择器使用）")
    @PlatformLogAnnotation(value = "查询字典列表（仅供前端下拉选择器使用）")
    public MyResult<List<DictSelectVO>> listDictWithSelect() {
        List<DictSelectVO> dictWithSelect = dictService.listDictWithSelect();
        return MyResult.success(dictWithSelect);
    }

    @GetMapping("/{did}")
    @PreAuthorize("hasAuthority('admin.dict.view')")
    @Operation(summary = "查询字典详情")
    @PlatformLogAnnotation(value = "查询字典详情")
    public MyResult<DictEntity> getDictById(@PathVariable("did") Long dictId) {
        DictEntity dict = dictService.getById(dictId);
        return MyResult.success(dict);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('admin.dict.add')")
    @Operation(summary = "保存字典")
    @PlatformLogAnnotation(value = "保存字典")
    public MyResult<Void> saveDict(@RequestBody @Validated(AddGroup.class) DictEntity dictEntity) {
        dictService.saveDict(dictEntity);
        return MyResult.success();
    }

    @DeleteMapping("/{did}")
    @PreAuthorize("hasAuthority('admin.dict.delete')")
    @Operation(summary = "删除字典")
    @PlatformLogAnnotation(value = "删除字典")
    public MyResult<Void> deleteDictById(@PathVariable("did") Long dictId) {
        dictService.deleteDictById(dictId);
        return MyResult.success();
    }

    @PutMapping
    @PreAuthorize("hasAuthority('admin.dict.update')")
    @Operation(summary = "保存权限")
    @PlatformLogAnnotation(value = "保存权限")
    public MyResult<Void> updateDict(@RequestBody @Validated(UpdateGroup.class) DictEntity dictEntity) {
        dictService.updateDict(dictEntity);
        return MyResult.success();
    }


}
