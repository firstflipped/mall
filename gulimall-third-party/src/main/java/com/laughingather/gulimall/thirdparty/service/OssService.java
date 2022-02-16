package com.laughingather.gulimall.thirdparty.service;

import java.util.Map;

/**
 * oss文件上传逻辑接口
 *
 * @author：laughingather
 * @create：2022-01-12 2022/1/12
 */
public interface OssService {

    /**
     * 获取文件上传所需签名
     *
     * @return
     */
    Map<String, String> policy();

}

