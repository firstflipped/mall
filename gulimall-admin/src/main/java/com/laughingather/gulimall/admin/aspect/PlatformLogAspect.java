package com.laughingather.gulimall.admin.aspect;


import cn.hutool.core.net.NetUtil;
import com.laughingather.gulimall.admin.annotation.PlatformLogAnnotation;
import com.laughingather.gulimall.admin.service.PlatformLogService;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.common.entity.PlatformLog;
import com.laughingather.gulimall.common.util.HttpContextUtil;
import com.laughingather.gulimall.common.util.JsonUtil;
import com.laughingather.gulimall.common.util.RequestUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * 日志切面
 * 在执行目标方法之前执行该切面，记录请求日志
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Aspect
@Order(1)
@Component
public class PlatformLogAspect {

    private final static Logger logger = LoggerFactory.getLogger(PlatformLogAspect.class);

    @Resource
    private PlatformLogService platformLogService;


    @Pointcut("@annotation(com.laughingather.gulimall.admin.annotation.PlatformLogAnnotation)")
    public void platformLog() {
    }


    @Around("platformLog()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        StopWatch stopWatch = StopWatch.createStarted();

        // 执行目标方法
        Object result = point.proceed();

        stopWatch.stop();
        long time = stopWatch.getTime(TimeUnit.MILLISECONDS);

        // 保存日志
        saveLog(point, result, time);

        return result;
    }


    /**
     * 保存日志
     *
     * @param point  方法信息
     * @param result 执行目标方法返回对象
     * @param time   执行时间
     */
    private void saveLog(ProceedingJoinPoint point, Object result, long time) {
        // 获取request对象
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();

        // 获取当前方法的信息
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        PlatformLog platformLog = new PlatformLog();

        PlatformLogAnnotation platformLogAnnotation = method.getAnnotation(PlatformLogAnnotation.class);
        if (platformLogAnnotation != null) {
            platformLog.setMethodType(platformLogAnnotation.type());
            platformLog.setMethodDescription(platformLogAnnotation.value());
            platformLog.setLogin(platformLogAnnotation.login() ? 1 : 0);
        }

        // 判断请求是否成功
        if (result instanceof MyResult) {
            MyResult myResult = (MyResult) result;
            if (myResult.isSuccess()) {
                platformLog.setSuccess(1);
            } else {
                platformLog.setSuccess(0);
            }
        }

        platformLog.setSpendTime(time);
        platformLog.setUri(request.getRequestURI());
        platformLog.setUrl(request.getRequestURL().toString());
        platformLog.setClassName(point.getTarget().getClass().getName());
        platformLog.setMethodName(method.getName() + "()");

        Object params = getParams(method, point.getArgs());
        if (!Objects.isNull(params)) {
            platformLog.setMethodParams(JsonUtil.obj2String(params));
        }

        platformLog.setServerIp(NetUtil.getLocalhostStr());
        platformLog.setClientIp(RequestUtil.getClientIp(request));
        platformLog.setCreateTime(LocalDateTime.now());

        logger.info(platformLog.toString());
        // 把日志放进消息队列
        // kafkaProducerService.sendMessage("user-logs", JsonUtil.obj2String(platformLog));

        // 把日志放进es
        // esSaveService.savePlatformLog2Es(platformLog);

        // 把日志放进数据库
        platformLogService.saveLog(platformLog);
    }

    /**
     * 根据方法和传入的参数获取请求参数
     *
     * @param method       方法信息
     * @param paramsValues 请求参数值列表
     */
    private Object getParams(Method method, Object[] paramsValues) {
        List<Object> argList = new ArrayList<>();
        // 获取请求参数键集合
        Parameter[] paramsKeys = method.getParameters();

        for (int i = 0; i < paramsKeys.length; i++) {
            // 将RequestBody注解修饰的参数作为请求参数
            RequestBody requestBody = paramsKeys[i].getAnnotation(RequestBody.class);
            if (requestBody != null) {
                argList.add(paramsValues[i]);
            }

            // 将@RequestParam注解修饰的参数作为请求参数
            RequestParam requestParam = paramsKeys[i].getAnnotation(RequestParam.class);
            assembleParams(requestParam != null, paramsKeys, paramsValues, i, requestParam.value(), argList);

            // 将@PathVariable注解修饰的参数作为请求参数
            PathVariable pathVariable = paramsKeys[i].getAnnotation(PathVariable.class);
            assembleParams(pathVariable != null, paramsKeys, paramsValues, i, pathVariable.value(), argList);

            // 将@ModelAttribute注解修饰的参数作为请求参数
            ModelAttribute modelAttribute = paramsKeys[i].getAnnotation(ModelAttribute.class);
            assembleParams(modelAttribute != null, paramsKeys, paramsValues, i, modelAttribute.value(), argList);

        }

        if (argList.size() == 0) {
            return null;
        } else if (argList.size() == 1) {
            return argList.get(0);
        } else {
            return argList;
        }
    }


    /**
     * @param isNotNull    注解是否为空注解
     * @param paramsKeys   参数键列表
     * @param paramsValues 参数值列表
     * @param argList      最终返回参数列表
     * @param i            遍历计数器
     * @param value        注解标注值，例如@RequestParam(value = "xxx")
     */
    private void assembleParams(boolean isNotNull, Parameter[] paramsKeys, Object[] paramsValues,
                                int i, String value, List<Object> argList) {
        if (isNotNull) {
            Map<String, Object> map = new HashMap<>(8);
            String key = paramsKeys[i].getName();
            // 如果注解中设置了键值，则覆盖默认的属性名
            if (StringUtils.isNotBlank(value)) {
                key = value;
            }
            map.put(key, paramsValues[i]);
            argList.add(map);
        }
    }


}
