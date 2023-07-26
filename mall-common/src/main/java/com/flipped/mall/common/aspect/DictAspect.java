package com.flipped.mall.common.aspect;

import com.alibaba.fastjson2.JSONObject;
import com.flipped.mall.common.aspect.annotation.Dict;
import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.common.util.JsonUtil;
import com.flipped.mall.common.util.ReflectUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 字典值转换切面
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2023-07-25 14:20:26
 */
@Slf4j
@Aspect
@Order(99)
@Component
public class DictAspect {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Pointcut("@annotation(com.flipped.mall.common.aspect.annotation.AutoDict)")
    public void dict() {
    }

    @AfterReturning(pointcut = "dict()", returning = "result")
    public Object doAround(Object result) {
        StopWatch stopWatch = StopWatch.createStarted();

        try {
            parseDict(result);
        } catch (Exception e) {
            // 字典转换错误不要影响业务
            log.error("dict parse exception", e);
        }

        stopWatch.stop();
        long time = stopWatch.getTime(TimeUnit.MILLISECONDS);
        // 耗时
        log.info("解析字典值耗时：{} 毫秒", time);

        return result;
    }


    /**
     * 解析字典值
     *
     * @param result 返回结果集
     */
    private Object parseDict(Object result) {

        if (!(result instanceof MyResult)) {
            return result;
        }

        List<JSONObject> jsonObjectList = new ArrayList<>();
        // 字典数据列表
        // key = 字典code value=数据列表
        Map<String, Set<String>> dataListMap = new HashMap<>(5);

        // 分页的情况
        if (((MyResult<?>) result).getData() instanceof MyPage) {
            List<?> list = ((MyPage<?>) ((MyResult<?>) result).getData()).getList();

            // 检测返回结果集中是否包含 Dict 字段注解
            Boolean hasDict = checkHasDict(list);
            // 不包含 Dict 字段注解，直接返回
            if (!hasDict) {
                return result;
            }
            // 包含 Dict 字段注解，解析字典值
            list.forEach(item -> {
                String json = JsonUtil.bean2Json(item);
                JSONObject jsonObject = JSONObject.parseObject(json);

                // 遍历字段，判断是否有字典注解
                Arrays.stream(ReflectUtil.getAllFields(item)).forEach(field -> {
                    String value = jsonObject.getString(field.getName());
                    // 如果值为空，直接返回
                    if (StringUtils.isBlank(value)) {
                        return;
                    }
                    // 如果没有 Dict 注解，直接返回
                    if (!field.isAnnotationPresent(Dict.class)) {
                        return;
                    }

                    Dict dict = field.getAnnotation(Dict.class);
                    String dictCode = dict.value();
                    if (StringUtils.isBlank(dictCode)) {
                        return;
                    }

                    // 查询字典数据，加入字典翻译字段
                    jsonObject.put(field.getName() + "Value", "字典翻译字段");
                });

                jsonObjectList.add(jsonObject);
            });

            (((MyResult<MyPage>) result).getData()).setList(jsonObjectList);
        }
        // 不分页，返回列表的情况
        else if (((MyResult<?>) result).getData() instanceof List) {
            List<?> list = (List<?>) ((MyResult<?>) result).getData();

            // 检测返回结果集中是否包含 Dict 字段注解
            Boolean hasDict = checkHasDict(list);
            // 不包含 Dict 字段注解，直接返回
            if (!hasDict) {
                return result;
            }
            // 包含 Dict 字段注解，解析字典值
            list.forEach(item -> {
                String json = JsonUtil.bean2Json(item);
                JSONObject jsonObject = JSONObject.parseObject(json);

                // 遍历字段，判断是否有字典注解
                Arrays.stream(ReflectUtil.getAllFields(item)).forEach(field -> {
                    String value = jsonObject.getString(field.getName());
                    // 如果值为空，直接返回
                    if (StringUtils.isBlank(value)) {
                        return;
                    }
                    // 如果没有 Dict 注解，直接返回
                    if (!field.isAnnotationPresent(Dict.class)) {
                        return;
                    }

                    Dict dict = field.getAnnotation(Dict.class);
                    String dictCode = dict.value();
                    if (StringUtils.isBlank(dictCode)) {
                        return;
                    }

                    // 查询字典数据，加入字典翻译字段
                    jsonObject.put(field.getName() + "Value", "字典翻译字段");
                });

                jsonObjectList.add(jsonObject);
            });

            ((MyResult<List<?>>) result).setData(jsonObjectList);
        }
        // 不分页，返回单条记录的情况
        else {
            Object item = ((MyResult<?>) result).getData();

            // 检测返回结果集中是否包含 Dict 字段注解
            Boolean hasDict = checkHasDict(Collections.singletonList(item));
            // 不包含 Dict 字段注解，直接返回
            if (!hasDict) {
                return result;
            }
            // 包含 Dict 字段注解，解析字典值
            String json = JsonUtil.bean2Json(item);
            JSONObject jsonObject = JSONObject.parseObject(json);

            // 遍历字段，判断是否有字典注解
            Arrays.stream(ReflectUtil.getAllFields(item)).forEach(field -> {
                String value = jsonObject.getString(field.getName());
                // 如果值为空，直接返回
                if (StringUtils.isBlank(value)) {
                    return;
                }
                // 如果没有 Dict 注解，直接返回
                if (!field.isAnnotationPresent(Dict.class)) {
                    return;
                }

                Dict dict = field.getAnnotation(Dict.class);
                String dictCode = dict.value();
                if (StringUtils.isBlank(dictCode)) {
                    return;
                }

                // 查询字典数据，加入字典翻译字段
                jsonObject.put(field.getName() + "Value", "字典翻译字段");
            });


            ((MyResult) result).setData(jsonObject);
        }

        return result;

    }


    /**
     * 检测返回结果集中是否包含 Dict 字段注解
     *
     * @param list
     * @return
     */
    private Boolean checkHasDict(List<?> list) {
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }

        // 遍历字段，判断是否有字典注解
        for (Field allField : ReflectUtil.getAllFields(list.get(0))) {
            if (allField.isAnnotationPresent(Dict.class)) {
                return true;
            }
        }

        return false;
    }

}
