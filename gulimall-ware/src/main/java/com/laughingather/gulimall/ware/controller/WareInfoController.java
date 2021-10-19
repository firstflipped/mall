package com.laughingather.gulimall.ware.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.ware.entity.WareInfoEntity;
import com.laughingather.gulimall.ware.entity.query.WareInfoQuery;
import com.laughingather.gulimall.ware.entity.vo.FareVO;
import com.laughingather.gulimall.ware.service.WareInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


/**
 * 仓库信息
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:57:23
 */
@RestController
@RequestMapping("/ware/wareinfo")
public class WareInfoController {
    @Autowired
    private WareInfoService wareInfoService;

    @GetMapping("/page")
    public MyResult<MyPage<WareInfoEntity>> pageWareInfoByParams(@ModelAttribute WareInfoQuery wareInfoQuery) {
        MyPage<WareInfoEntity> wareInfoMyPage = wareInfoService.pageWareInfoByParams(wareInfoQuery);
        return MyResult.success(wareInfoMyPage);
    }

    @GetMapping("/list")
    public MyResult<List<WareInfoEntity>> listWareInfo() {
        List<WareInfoEntity> wareInfoList = wareInfoService.list();
        return MyResult.success(wareInfoList);
    }

    @GetMapping("/{id}")
    public MyResult<WareInfoEntity> getWareInfoById(@PathVariable("id") Long id) {
        WareInfoEntity wareInfo = wareInfoService.getById(id);
        return MyResult.success(wareInfo);
    }

    @GetMapping("/fare")
    public MyResult<FareVO> getFare(@RequestParam("aid") Long addressId) {
        FareVO fareVO = wareInfoService.getFare(addressId);
        return MyResult.success(fareVO);
    }

    @PostMapping
    public MyResult saveWareInfo(@RequestBody WareInfoEntity wareInfo) {
        wareInfoService.save(wareInfo);
        return MyResult.success();
    }

    @PutMapping
    public MyResult updateWareInfoById(@RequestBody WareInfoEntity wareInfo) {
        wareInfoService.updateById(wareInfo);
        return MyResult.success();
    }

    @DeleteMapping
    public MyResult deleteWareInfoByIds(@RequestBody Long[] ids) {
        wareInfoService.removeByIds(Arrays.asList(ids));
        return MyResult.success();
    }

}
