package com.laughingather.gulimall.product.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.common.valid.AddGroup;
import com.laughingather.gulimall.common.valid.UpdateGroup;
import com.laughingather.gulimall.product.entity.param.AttrParam;
import com.laughingather.gulimall.product.entity.query.AttrQuery;
import com.laughingather.gulimall.product.entity.vo.AttrVO;
import com.laughingather.gulimall.product.service.AttrService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 属性管理路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/product/attr")
@Tag(name = "属性管理")
public class AttrController {

    @Resource
    private AttrService attrService;

    @GetMapping("/page")
    @Operation(summary = "分页查询属性列表")
    public MyResult<MyPage<AttrVO>> listAttrsWithPage(@ModelAttribute AttrQuery attrQuery) {
        MyPage<AttrVO> attrsWithPage = attrService.listAttrsWithPage(attrQuery);
        return MyResult.success(attrsWithPage);
    }


    @GetMapping("/{cid}/page")
    @Operation(summary = "根据属性类型和属性分类分页查询属性列表")
    @Parameters({
            @Parameter(name = "cid", description = "属性分类id", example = "225"),
            @Parameter(name = "attrQuery", description = "属性查询过滤条件")
    })
    public MyResult<MyPage<AttrVO>> listAttrsWithPageByCategoryId(@PathVariable("cid") Long categoryId,
                                                                  @ModelAttribute AttrQuery attrQuery) {
        MyPage<AttrVO> attrsWithPage = attrService.listAttrsWithPageByCategoryId(categoryId, attrQuery);
        return MyResult.success(attrsWithPage);
    }


    @GetMapping("/{aid}")
    @Operation(summary = "查询属性详情信息")
    @Parameter(name = "aid", description = "属性id", example = "7")
    public MyResult<AttrVO> getAttrVOById(@PathVariable("aid") Long attrId) {
        AttrVO attrVO = attrService.getAttrVOById(attrId);
        return MyResult.success(attrVO);
    }

    @PostMapping
    @Operation(summary = "保存属性信息")
    public MyResult<Void> saveAttr(@Validated({AddGroup.class}) @RequestBody AttrParam attrParam) {
        attrService.saveAttr(attrParam);
        return MyResult.success();
    }

    @PutMapping
    @Operation(summary = "更新属性信息")
    public MyResult<Void> updateAttrById(@Validated({UpdateGroup.class}) @RequestBody AttrParam attrParam) {
        attrService.updateAttrById(attrParam);
        return MyResult.success();
    }


}
