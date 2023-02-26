package com.flipped.mall.ware.controller;

import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.ware.entity.WareInfoEntity;
import com.flipped.mall.ware.entity.query.WareInfoQuery;
import com.flipped.mall.ware.entity.vo.FareVO;
import com.flipped.mall.ware.service.WareInfoService;
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
public class WareInfoController {

    @Resource
    private WareInfoService wareInfoService;

    @GetMapping("/page")
    public MyResult<MyPage<WareInfoEntity>> listWaresWithPage(@ModelAttribute WareInfoQuery wareInfoQuery) {
        MyPage<WareInfoEntity> wareInfoMyPage = wareInfoService.listWaresWithPage(wareInfoQuery);
        return MyResult.success(wareInfoMyPage);
    }

    @GetMapping("/list")
    public MyResult<List<WareInfoEntity>> listWares() {
        List<WareInfoEntity> wareInfoList = wareInfoService.list();
        return MyResult.success(wareInfoList);
    }

    @GetMapping("/{wid}")
    public MyResult<WareInfoEntity> getWareById(@PathVariable("wid") Long wareId) {
        WareInfoEntity wareInfo = wareInfoService.getById(wareId);
        return MyResult.success(wareInfo);
    }

    @GetMapping("/fare")
    public MyResult<FareVO> getFare(@RequestParam("aid") Long addressId) {
        FareVO fareVO = wareInfoService.getFare(addressId);
        return MyResult.success(fareVO);
    }

    @PostMapping
    public MyResult<Void> saveWare(@RequestBody WareInfoEntity wareInfo) {
        wareInfoService.save(wareInfo);
        return MyResult.success();
    }

    @PutMapping
    public MyResult<Void> updateWareById(@RequestBody WareInfoEntity wareInfo) {
        wareInfoService.updateById(wareInfo);
        return MyResult.success();
    }

    @DeleteMapping
    public MyResult<Void> deleteBatchWareByIds(@RequestBody Long[] ids) {
        wareInfoService.removeByIds(Arrays.asList(ids));
        return MyResult.success();
    }

}
