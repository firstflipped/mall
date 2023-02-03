package com.laughingather.gulimall.admin.controller;

import com.laughingather.gulimall.admin.entity.DictEntity;
import com.laughingather.gulimall.admin.entity.query.DictQuery;
import com.laughingather.gulimall.admin.service.DictService;
import com.laughingather.gulimall.common.annotation.PlatformLogAnnotation;
import com.laughingather.gulimall.common.entity.api.MyPage;
import com.laughingather.gulimall.common.entity.api.MyResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

}
