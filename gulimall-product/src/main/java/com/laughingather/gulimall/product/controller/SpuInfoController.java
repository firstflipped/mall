package com.laughingather.gulimall.product.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.product.entity.SpuInfoEntity;
import com.laughingather.gulimall.product.entity.dto.SpuSaveDTO;
import com.laughingather.gulimall.product.entity.query.SpuInfoQuery;
import com.laughingather.gulimall.product.service.SpuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * spu信息
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
@RestController
@RequestMapping("product/spuinfo")
public class SpuInfoController {

    @Autowired
    private SpuInfoService spuInfoService;

    @GetMapping("/page")
    public MyResult<MyPage<SpuInfoEntity>> pageSpuInfoByParams(@ModelAttribute SpuInfoQuery spuInfoQuery) {
        MyPage<SpuInfoEntity> spuInfoMyPage = spuInfoService.pageSpuInfoByParams(spuInfoQuery);
        return MyResult.success(spuInfoMyPage);
    }

    @PostMapping
    public MyResult saveSpuInfo(@RequestBody SpuSaveDTO spuSaveDTO) {
        spuInfoService.saveSpuInfo(spuSaveDTO);
        return MyResult.success();
    }

}
