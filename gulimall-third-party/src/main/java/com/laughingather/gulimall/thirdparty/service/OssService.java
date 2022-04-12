package com.laughingather.gulimall.thirdparty.service;

import java.util.Map;

/**
 * oss文件上传逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface OssService {

    /**
     * 获取文件上传所需签名
     *
     * @return
     */
    Map<String, String> policy();

}

