package com.laughingather.gulimall.common.valid;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

/**
 * 组序列验证组
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-12-01 10:24:52
 */
@GroupSequence({AddGroup.class, UpdateGroup.class, Default.class})
public interface Group {
}
