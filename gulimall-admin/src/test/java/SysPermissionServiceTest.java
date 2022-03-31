import com.laughingather.gulimall.admin.entity.SysPermissionEntity;
import com.laughingather.gulimall.common.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 权限测试类
 *
 * @author：laughingather
 * @create：2021-12-02 2021/12/2
 */
@Slf4j
@SpringBootTest(classes = com.laughingather.gulimall.admin.GulimallAdminApplication.class)
public class SysPermissionServiceTest {

    @Test
    public void pojo2Json() {
        SysPermissionEntity sysPermissionEntity = new SysPermissionEntity();
        String json = JsonUtil.obj2String(sysPermissionEntity);

        log.info(json);
    }


}

