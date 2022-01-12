package com.laughingather.gulimall.product.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.product.entity.SpuInfoEntity;
import com.laughingather.gulimall.product.entity.param.SpuParam;
import com.laughingather.gulimall.product.entity.query.SpuInfoQuery;
import com.laughingather.gulimall.product.service.SpuInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * spu信息
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
@RestController
@RequestMapping("/product/spu-info")
@Api(tags = "spu模块")
public class SpuInfoController {

    @Resource
    private SpuInfoService spuInfoService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询spu列表")
    public MyResult<MyPage<SpuInfoEntity>> listSpuWithPage(@ModelAttribute SpuInfoQuery spuInfoQuery) {
        MyPage<SpuInfoEntity> spuInfoMyPage = spuInfoService.listSpuWithPage(spuInfoQuery);
        return MyResult.success(spuInfoMyPage);
    }

    /**
     * 商品上架
     *
     * @return
     */
    @PostMapping("/{sid}/up")
    @ApiOperation(value = "商品上架")
    @ApiImplicitParam(name = "sid", value = "spuId")
    public MyResult<Void> upSpuBySpuId(@PathVariable("sid") Long spuId) {
        spuInfoService.upSpu(spuId);
        return MyResult.success();
    }


    @PostMapping
    @ApiOperation(value = "保存spu信息")
    public MyResult<Void> saveSpuInfo(@RequestBody SpuParam spuParam) {
        spuInfoService.saveSpuInfo(spuParam);
        return MyResult.success();
    }

}
