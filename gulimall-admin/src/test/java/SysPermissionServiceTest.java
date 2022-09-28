import com.laughingather.gulimall.admin.entity.SysPermissionEntity;
import com.laughingather.gulimall.admin.entity.vo.PermissionsWithTreeVO;
import com.laughingather.gulimall.admin.mapper.SysPermissionMapper;
import com.laughingather.gulimall.admin.service.SysPermissionService;
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
public class SysPermissionServiceTest {


    @Resource
    private SysPermissionMapper sysPermissionMapper;
    @Resource
    private SysPermissionService sysPermissionService;

    @Test
    public void testSelectPermissionsVO() {
        List<PermissionsWithTreeVO> permissionsWithTreeVOS = sysPermissionMapper.selectPermissionsVO();
        log.info(permissionsWithTreeVOS.toString());
    }

    @Test
    public void testSavePermission() {
        SysPermissionEntity permission = new SysPermissionEntity();
        permission.setPermissionName("角色删除");
        permission.setPermissionValue("admin:permission:delete");
        permission.setType(3);
        permission.setStatus(1);
        permission.setSortNo(1);
        permission.setParentId(1466211572253855744L);
        permission.setDescription("角色删除控制");

        sysPermissionService.savePermission(permission);
    }

}

