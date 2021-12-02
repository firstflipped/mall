import com.laughingather.gulimall.adminnew.entity.vo.PermissionsWithTreeVO;
import com.laughingather.gulimall.adminnew.mapper.SysPermissionMapper;
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
@SpringBootTest(classes = com.laughingather.gulimall.adminnew.GulimallAdminNewApplication.class)
public class SysPermissionMapperTest {

    @Resource
    private SysPermissionMapper sysPermissionMapper;

    @Test
    public void testSelectPermissionsVO() {
        List<PermissionsWithTreeVO> permissionsWithTreeVOS = sysPermissionMapper.selectPermissionsVO();
        log.info(permissionsWithTreeVOS.toString());
    }

}

