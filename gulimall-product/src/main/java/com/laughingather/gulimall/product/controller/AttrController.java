package com.laughingather.gulimall.product.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.product.entity.dto.AttrDTO;
import com.laughingather.gulimall.product.entity.query.AttrQuery;
import com.laughingather.gulimall.product.entity.vo.AttrVO;
import com.laughingather.gulimall.product.service.AttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 商品属性
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {

    @Autowired
    private AttrService attrService;

    @GetMapping("/{attrType}/page/{catId}")
    public MyResult<MyPage<AttrVO>> pageAttrsByCatId(@PathVariable("catId") Long catId,
                                                     @PathVariable("attrType") String attrType,
                                                     @ModelAttribute AttrQuery attrQuery) {
        MyPage<AttrVO> attrEntityMyPage = attrService.pageAttrsByCatId(catId, attrType, attrQuery);
        return MyResult.success(attrEntityMyPage);
    }


    @GetMapping("/{attrId}")
    public MyResult<AttrVO> getAttrVOById(@PathVariable Long attrId) {
        AttrVO attrVO = attrService.getAttrVOById(attrId);
        return MyResult.success(attrVO);
    }

    @PostMapping
    public MyResult saveAttr(@RequestBody AttrDTO attrDTO) {
        attrService.saveAttr(attrDTO);
        return MyResult.success();
    }

    @PutMapping
    public MyResult updateAttrById(@RequestBody AttrDTO attrDTO) {
        attrService.updateAttrById(attrDTO);
        return MyResult.success();
    }


}
