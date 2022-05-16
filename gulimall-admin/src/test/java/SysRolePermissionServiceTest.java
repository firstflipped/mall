import com.laughingather.gulimall.admin.entity.SysPermissionEntity;
import com.laughingather.gulimall.admin.service.SysRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色权限关联测试类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-21 10:34:06
 */
@Slf4j
@SpringBootTest(classes = com.laughingather.gulimall.admin.GulimallAdminApplication.class)
public class SysRolePermissionServiceTest {

    @Resource
    private SysRolePermissionService sysRolePermissionService;

    @Test
    public void listPermissionsByUserid() {
        List<SysPermissionEntity> permissions = sysRolePermissionService.listPermissionsByUserid(1463452828315029504L);
        log.info("{}", permissions);
    }


}
