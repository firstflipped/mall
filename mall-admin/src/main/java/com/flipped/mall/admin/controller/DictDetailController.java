package com.flipped.mall.admin.controller;

import com.flipped.mall.admin.entity.DictDetailEntity;
import com.flipped.mall.admin.service.DictDetailService;
import com.flipped.mall.common.annotation.PlatformLog;
import com.flipped.mall.common.constant.LogConstants;
import com.flipped.mall.common.entity.api.MyResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 字典明细管理
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-12-21 15:12:15
 */
@RestController
@RequestMapping("/admin/dict-detail")
public class DictDetailController {

    @Resource
    private DictDetailService dictDetailService;

    /**
     * 保存字典明细
     *
     * @param dictDetailEntity 字典明细信息
     * @return MyResult<Void>
     */
    @PostMapping
    @PreAuthorize("hasAuthority('admin.dictdetail.view')")
    @PlatformLog(value = "保存字典明细", type = LogConstants.INSERT)
    public MyResult<Void> saveDictDetail(@RequestBody DictDetailEntity dictDetailEntity) {
        dictDetailService.saveDictDetail(dictDetailEntity);
        return MyResult.success();
    }


}
