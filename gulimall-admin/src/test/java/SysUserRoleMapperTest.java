import com.laughingather.gulimall.admin.entity.SysRoleEntity;
import com.laughingather.gulimall.admin.mapper.SysUserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author：laughingather
 * @create：2021-12-02 2021/12/2
 */
@Slf4j
@SpringBootTest(classes = com.laughingather.gulimall.admin.GulimallAdminApplication.class)
public class SysUserRoleMapperTest {

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Test
    public void testSelectPermissionsVO() {
        List<SysRoleEntity> roles = sysUserRoleMapper.listRolesByUserid(1463452828315029504L);
        log.info("{}", roles);
    }

}

