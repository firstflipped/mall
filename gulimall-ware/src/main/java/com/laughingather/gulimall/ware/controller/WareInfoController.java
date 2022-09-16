package com.laughingather.gulimall.ware.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.ware.entity.WareInfoEntity;
import com.laughingather.gulimall.ware.entity.query.WareInfoQuery;
import com.laughingather.gulimall.ware.entity.vo.FareVO;
import com.laughingather.gulimall.ware.service.WareInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


/**
 * 仓库信息
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/ware/ware-info")
@Tag(name = "仓库模块")
public class WareInfoController {

    @Resource
    private WareInfoService wareInfoService;

    @GetMapping("/page")
    @Operation(summary = "分页查询仓库列表")
    public MyResult<MyPage<WareInfoEntity>> listWaresWithPage(@ModelAttribute WareInfoQuery wareInfoQuery) {
        MyPage<WareInfoEntity> wareInfoMyPage = wareInfoService.listWaresWithPage(wareInfoQuery);
        return MyResult.success(wareInfoMyPage);
    }

    @GetMapping("/list")
    @Operation("查询仓库列表")
    public MyResult<List<WareInfoEntity>> listWares() {
        List<WareInfoEntity> wareInfoList = wareInfoService.list();
        return MyResult.success(wareInfoList);
    }

    @GetMapping("/{wid}")
    @Operation(summary = "查询仓库信息详情")
    public MyResult<WareInfoEntity> getWareById(@PathVariable("wid") Long wareId) {
        WareInfoEntity wareInfo = wareInfoService.getById(wareId);
        return MyResult.success(wareInfo);
    }

    @GetMapping("/fare")
    @Operation(summary = "查询运费信息")
    public MyResult<FareVO> getFare(@RequestParam("aid") Long addressId) {
        FareVO fareVO = wareInfoService.getFare(addressId);
        return MyResult.success(fareVO);
    }

    @PostMapping
    @Operation(summary = "保存仓库信息")
    public MyResult<Void> saveWare(@RequestBody WareInfoEntity wareInfo) {
        wareInfoService.save(wareInfo);
        return MyResult.success();
    }

    @PutMapping
    @Operation(summary = "更新仓库信息")
    public MyResult<Void> updateWareById(@RequestBody WareInfoEntity wareInfo) {
        wareInfoService.updateById(wareInfo);
        return MyResult.success();
    }

    @DeleteMapping
    @Operation(summary = "批量删除仓库信息")
    public MyResult<Void> deleteBatchWareByIds(@RequestBody Long[] ids) {
        wareInfoService.removeByIds(Arrays.asList(ids));
        return MyResult.success();
    }

}
