package com.laughingather.gulimall.adminnew.controller;

import com.laughingather.gulimall.adminnew.entity.SysPermissionEntity;
import com.laughingather.gulimall.adminnew.entity.vo.PermissionsWithTreeVO;
import com.laughingather.gulimall.adminnew.service.SysPermissionService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限路由
 *
 * @author：laughingather
 * @create：2021-12-01 2021/12/1
 */
@RestController
@RequestMapping("/api/permission")
public class SysPermissionController {

    @Resource
    private SysPermissionService sysPermissionService;

    @PostMapping
    public MyResult savePermission(@RequestBody SysPermissionEntity sysPermissionEntity) {
        sysPermissionService.savePermission(sysPermissionEntity);
        return MyResult.success();
    }

    @DeleteMapping
    public MyResult deletePermissionByIds(@RequestBody List<Long> permissionIds) {
        sysPermissionService.batchDeletePermission(permissionIds);
        return MyResult.success();
    }

    @DeleteMapping("/{pid}")
    public MyResult deletePermissionById(@PathVariable("pid") Long permissionId) {
        sysPermissionService.deletePermission(permissionId);
        return MyResult.success();
    }

    @PutMapping
    public MyResult updatePermissionById(@RequestBody SysPermissionEntity sysPermissionEntity) {
        sysPermissionService.updatePermission(sysPermissionEntity);
        return MyResult.success();
    }

    @GetMapping("/list")
    public MyResult<List<SysPermissionEntity>> listPermissions() {
        List<SysPermissionEntity> permissions = sysPermissionService.listPermissions();
        return MyResult.success(permissions);
    }

    @GetMapping("/page")
    public MyResult<MyPage<SysPermissionEntity>> listPermissionsWithPage(@RequestParam(value = "pn", defaultValue = "1") Integer pageNum,
                                                                         @RequestParam(value = "ps", defaultValue = "10") Integer pageSize) {
        MyPage<SysPermissionEntity> permissionsWithPage = sysPermissionService.listPermissionsWithPage(pageNum, pageSize);
        return MyResult.success(permissionsWithPage);
    }


    @GetMapping("/page")
    public MyResult<List<PermissionsWithTreeVO>> listPermissionsWithTree() {
        List<PermissionsWithTreeVO> permissionsWithTree = sysPermissionService.listPermissionsWithTree();
        return MyResult.success(permissionsWithTree);
    }

}

