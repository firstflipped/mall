package com.flipped.mall.product.controller;

import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.common.valid.AddGroup;
import com.flipped.mall.common.valid.UpdateGroup;
import com.flipped.mall.product.entity.param.AttrParam;
import com.flipped.mall.product.entity.query.AttrQuery;
import com.flipped.mall.product.entity.vo.AttrVO;
import com.flipped.mall.product.service.AttrService;
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
public class AttrController {

    @Resource
    private AttrService attrService;

    @GetMapping("/page")
    public MyResult<MyPage<AttrVO>> listAttrsWithPage(@ModelAttribute AttrQuery attrQuery) {
        MyPage<AttrVO> attrsWithPage = attrService.listAttrsWithPage(attrQuery);
        return MyResult.success(attrsWithPage);
    }


    @GetMapping("/{cid}/page")
    public MyResult<MyPage<AttrVO>> listAttrsWithPageByCategoryId(@PathVariable("cid") Long categoryId,
                                                                  @ModelAttribute AttrQuery attrQuery) {
        MyPage<AttrVO> attrsWithPage = attrService.listAttrsWithPageByCategoryId(categoryId, attrQuery);
        return MyResult.success(attrsWithPage);
    }


    @GetMapping("/{aid}")
    public MyResult<AttrVO> getAttrVOById(@PathVariable("aid") Long attrId) {
        AttrVO attrVO = attrService.getAttrVOById(attrId);
        return MyResult.success(attrVO);
    }

    @PostMapping
    public MyResult<Void> saveAttr(@Validated({AddGroup.class}) @RequestBody AttrParam attrParam) {
        attrService.saveAttr(attrParam);
        return MyResult.success();
    }

    @PutMapping
    public MyResult<Void> updateAttrById(@Validated({UpdateGroup.class}) @RequestBody AttrParam attrParam) {
        attrService.updateAttrById(attrParam);
        return MyResult.success();
    }

}
