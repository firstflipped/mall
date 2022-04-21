import com.laughingather.gulimall.admin.entity.vo.PermissionsWithTreeVO;
import com.laughingather.gulimall.admin.mapper.SysPermissionMapper;
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
public class SysPermissionMapperTest {

    @Resource
    private SysPermissionMapper sysPermissionMapper;

    @Test
    public void testSelectPermissionsVO() {
        List<PermissionsWithTreeVO> permissionsWithTreeVOS = sysPermissionMapper.selectPermissionsVO();
        log.info(permissionsWithTreeVOS.toString());
    }

}

