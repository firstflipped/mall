package com.flipped.mall.product.controller;

import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.product.entity.param.SpuParam;
import com.flipped.mall.product.entity.query.SpuInfoQuery;
import com.flipped.mall.product.entity.vo.SpuInfoVO;
import com.flipped.mall.product.service.SpuInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * spu路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/product/spu")
public class SpuInfoController {

    @Resource
    private SpuInfoService spuInfoService;

    @GetMapping("/page")
    public MyResult<MyPage<SpuInfoVO>> listSpuWithPage(@ModelAttribute SpuInfoQuery spuInfoQuery) {
        MyPage<SpuInfoVO> spuInfoMyPage = spuInfoService.listSpuWithPage(spuInfoQuery);
        return MyResult.success(spuInfoMyPage);
    }


    @PostMapping("/{sid}/up")
    public MyResult<Void> upSpuBySpuId(@PathVariable("sid") Long spuId) {
        spuInfoService.upSpu(spuId);
        return MyResult.success();
    }


    @PostMapping
    public MyResult<Void> saveSpuInfo(@RequestBody SpuParam spuParam) {
        spuInfoService.saveSpuInfo(spuParam);
        return MyResult.success();
    }

}
