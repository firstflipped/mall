package com.laughingather.gulimall.admin.service;

import com.laughingather.gulimall.admin.entity.PermissionEntity;
import com.laughingather.gulimall.admin.entity.vo.PermissionsWithTreeVO;
import com.laughingather.gulimall.admin.mapper.PermissionMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-21 10:34:06
 */
@Slf4j
@SpringBootTest(classes = com.laughingather.gulimall.admin.GulimallAdminApplication.class)
public class PermissionServiceTest {


    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private PermissionService permissionService;

    @Test
    public void testSelectPermissionsVO() {
        List<PermissionsWithTreeVO> permissionsWithTreeVOS = permissionMapper.selectPermissionsVO();
        log.info(permissionsWithTreeVOS.toString());
    }

    @Test
    public void testSavePermission() {
        PermissionEntity permission = new PermissionEntity();
        permission.setPermissionName("角色删除");
        permission.setPermissionValue("admin:permission:delete");
        permission.setType(3);
        permission.setEnable(1);
        permission.setSort(1);
        permission.setParentId(1466211572253855744L);
        permission.setDescription("角色删除控制");

        permissionService.savePermission(permission);
    }

}

