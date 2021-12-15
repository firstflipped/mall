package com.laughingather.gulimall.product.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.product.entity.dto.AttrDTO;
import com.laughingather.gulimall.product.entity.query.AttrQuery;
import com.laughingather.gulimall.product.entity.vo.AttrVO;
import com.laughingather.gulimall.product.service.AttrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 属性路由
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
@RestController
@RequestMapping("/product/attr")
@Api(tags = "属性模块")
public class AttrController {

    @Resource
    private AttrService attrService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询属性列表")
    public MyResult<MyPage<AttrVO>> listAttrsWithPage(@ModelAttribute AttrQuery attrQuery) {
        MyPage<AttrVO> attrsWithPage = attrService.listAttrsWithPage(attrQuery);
        return MyResult.success(attrsWithPage);
    }


    @GetMapping("/{cid}/page")
    @ApiOperation("根据属性类型和属性分类分页查询属性列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "属性分类id", dataTypeClass = Long.class, example = "225"),
            @ApiImplicitParam(name = "attrQuery", value = "属性查询过滤条件", dataTypeClass = AttrQuery.class)
    })
    public MyResult<MyPage<AttrVO>> listAttrsWithPageByCategoryId(@PathVariable("cid") Long categoryId,
                                                                  @ModelAttribute AttrQuery attrQuery) {
        MyPage<AttrVO> attrsWithPage = attrService.listAttrsWithPageByCategoryId(categoryId, attrQuery);
        return MyResult.success(attrsWithPage);
    }


    @GetMapping("/{aid}")
    @ApiOperation(value = "查询属性详情信息")
    @ApiImplicitParam(name = "aid", value = "属性id", dataTypeClass = Long.class, example = "7")
    public MyResult<AttrVO> getAttrVOById(@PathVariable("aid") Long attrId) {
        AttrVO attrVO = attrService.getAttrVOById(attrId);
        return MyResult.success(attrVO);
    }

    @PostMapping
    @ApiOperation(value = "保存属性信息")
    public MyResult saveAttr(@RequestBody AttrDTO attrDTO) {
        attrService.saveAttr(attrDTO);
        return MyResult.success();
    }

    @PutMapping
    @ApiOperation(value = "更新属性信息")
    public MyResult updateAttrById(@RequestBody AttrDTO attrDTO) {
        attrService.updateAttrById(attrDTO);
        return MyResult.success();
    }


}
