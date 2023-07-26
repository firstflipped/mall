package com.flipped.mall.admin.controller;

import com.flipped.mall.admin.entity.DictEntity;
import com.flipped.mall.admin.entity.query.DictQuery;
import com.flipped.mall.admin.entity.vo.DictSelectVO;
import com.flipped.mall.admin.service.DictService;
import com.flipped.mall.common.aspect.annotation.PlatformLog;
import com.flipped.mall.common.constant.LogConstants;
import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.common.valid.AddGroup;
import com.flipped.mall.common.valid.UpdateGroup;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典管理
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-12-21 15:12:15
 */
@RestController
@RequestMapping("/admin/dict")
public class DictController {

    @Resource
    private DictService dictService;

    /**
     * 分页查询字典列表
     *
     * @param dictQuery 字典列表查询条件
     * @return 字典列表
     */
    @GetMapping("/page")
    @PreAuthorize("hasAuthority('admin.dict.view')")
    @PlatformLog(value = "分页查询字典列表")
    public MyResult<MyPage<DictEntity>> listDictWithPage(@ModelAttribute DictQuery dictQuery) {
        MyPage<DictEntity> dictWithPage = dictService.listDictWithPage(dictQuery);
        return MyResult.success(dictWithPage);
    }

    /**
     * 查询字典列表（仅供前端下拉选择器使用）
     *
     * @return 字典列表
     */
    @GetMapping("/list/select")
    @PlatformLog(value = "查询字典列表（仅供前端下拉选择器使用）")
    public MyResult<List<DictSelectVO>> listDictWithSelect() {
        List<DictSelectVO> dictWithSelect = dictService.listDictWithSelect();
        return MyResult.success(dictWithSelect);
    }

    /**
     * 查询字典详情
     *
     * @param dictId 字典id
     * @return 字典详情
     */
    @GetMapping("/{did}")
    @PreAuthorize("hasAuthority('admin.dict.view')")
    @PlatformLog(value = "查询字典详情")
    public MyResult<DictEntity> getDictById(@PathVariable("did") Long dictId) {
        DictEntity dict = dictService.getById(dictId);
        return MyResult.success(dict);
    }

    /**
     * 保存字典
     *
     * @param dictEntity 字典信息
     * @return MyResult<Void>
     */
    @PostMapping
    @PreAuthorize("hasAuthority('admin.dict.add')")
    @PlatformLog(value = "保存字典", type = LogConstants.INSERT)
    public MyResult<Void> saveDict(@RequestBody @Validated(AddGroup.class) DictEntity dictEntity) {
        dictService.saveDict(dictEntity);
        return MyResult.success();
    }

    /**
     * 删除字典
     *
     * @param dictId 字典id
     * @return MyResult<Void>
     */
    @DeleteMapping("/{did}")
    @PreAuthorize("hasAuthority('admin.dict.delete')")
    @PlatformLog(value = "删除字典", type = LogConstants.DELETE)
    public MyResult<Void> deleteDictById(@PathVariable("did") Long dictId) {
        dictService.deleteDictById(dictId);
        return MyResult.success();
    }

    /**
     * 更新字典
     *
     * @param dictEntity 字典信息
     * @return MyResult<Void>
     */
    @PutMapping
    @PreAuthorize("hasAuthority('admin.dict.update')")
    @PlatformLog(value = "更新字典", type = LogConstants.UPDATE)
    public MyResult<Void> updateDict(@RequestBody @Validated(UpdateGroup.class) DictEntity dictEntity) {
        dictService.updateDict(dictEntity);
        return MyResult.success();
    }


}
