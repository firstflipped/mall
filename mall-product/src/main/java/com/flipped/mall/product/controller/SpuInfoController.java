package com.flipped.mall.product.controller;

import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.product.entity.param.SpuParam;
import com.flipped.mall.product.entity.vo.SpuInfoVO;
import com.flipped.mall.product.service.SpuInfoService;
import com.flipped.mall.product.entity.query.SpuInfoQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @Parameter(name = "sid", description = "spuId")
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
