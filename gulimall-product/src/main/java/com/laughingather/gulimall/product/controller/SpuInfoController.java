package com.laughingather.gulimall.product.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.product.entity.param.SpuParam;
import com.laughingather.gulimall.product.entity.query.SpuInfoQuery;
import com.laughingather.gulimall.product.entity.vo.SpuInfoVO;
import com.laughingather.gulimall.product.service.SpuInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@Tag(name = "spu模块")
public class SpuInfoController {

    @Resource
    private SpuInfoService spuInfoService;

    @GetMapping("/page")
    @Operation(summary = "分页查询spu列表")
    public MyResult<MyPage<SpuInfoVO>> listSpuWithPage(@ModelAttribute SpuInfoQuery spuInfoQuery) {
        MyPage<SpuInfoVO> spuInfoMyPage = spuInfoService.listSpuWithPage(spuInfoQuery);
        return MyResult.success(spuInfoMyPage);
    }


    @PostMapping("/{sid}/up")
    @Operation(summary = "商品上架")
    @Parameter(name = "sid", value = "spuId")
    public MyResult<Void> upSpuBySpuId(@PathVariable("sid") Long spuId) {
        spuInfoService.upSpu(spuId);
        return MyResult.success();
    }


    @PostMapping
    @Operation(summary = "保存spu信息")
    public MyResult<Void> saveSpuInfo(@RequestBody SpuParam spuParam) {
        spuInfoService.saveSpuInfo(spuParam);
        return MyResult.success();
    }

}
