package com.flipped.mall.common.aspect;


import cn.hutool.core.net.NetUtil;
import com.flipped.mall.common.annotation.PlatformLog;
import com.flipped.mall.common.constant.AuthConstants;
import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.common.util.HttpContextUtil;
import com.flipped.mall.common.util.JsonUtil;
import com.flipped.mall.common.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * 日志切面
 * 在执行目标方法之前执行该切面，记录请求日志
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Slf4j
@Aspect
@Order(1)
@Component
public class PlatformLogAspect {

    @Pointcut("@annotation(com.flipped.mall.common.annotation.PlatformLog)")
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
        try {
            saveLog(point, result, time);
        } catch (Exception e) {
            // 记录日志错误不要影响业务
            log.error("log record parse exception", e);
        }

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
        com.flipped.mall.common.entity.PlatformLog platformLog = new com.flipped.mall.common.entity.PlatformLog();

        // 获取request对象
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();

        // 获取当前方法的信息
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();


        PlatformLog platformLogAnnotation = method.getAnnotation(PlatformLog.class);
        if (platformLogAnnotation != null) {
            platformLog.setMethodType(platformLogAnnotation.type());
            platformLog.setMethodDescription(platformLogAnnotation.value());
            platformLog.setLogin(platformLogAnnotation.login() ? 1 : 0);
        }

        platformLog.setRequestUri(request.getRequestURI());
        platformLog.setRequestUrl(String.valueOf(request.getRequestURL()));
        platformLog.setRequestMethod(request.getMethod());
        platformLog.setRequestParams(JsonUtil.bean2Json(getParams(method, point.getArgs())));

        // 类信息、方法信息
        platformLog.setClassName(point.getTarget().getClass().getName());
        platformLog.setMethodName(method.getName() + "()");

        // ip信息
        platformLog.setServerIp(NetUtil.getLocalhostStr());
        platformLog.setClientIp(RequestUtil.getClientIp(request));

        // 判断请求是否成功
        if (result instanceof MyResult) {
            MyResult myResult = (MyResult) result;
            if (myResult.getSuccess()) {
                platformLog.setSuccess(1);
            } else {
                platformLog.setSuccess(0);
            }
        }

        // 操作信息
        if (StringUtils.isNotBlank(request.getHeader(AuthConstants.USERID))) {
            platformLog.setOperationUserid(Long.parseLong(request.getHeader(AuthConstants.USERID)));
        }
        if (StringUtils.isNotBlank(request.getHeader(AuthConstants.USERNAME))) {
            platformLog.setOperationUsername(request.getHeader(AuthConstants.USERNAME));
        }
        platformLog.setSpendTime(time);
        platformLog.setOperationTime(LocalDateTime.now());

        // 把日志放进消息队列
        // kafkaProducerService.sendMessage("user-logs", JsonUtil.bean2Json(platformLog));

        // 把日志放进es
        // esSaveService.savePlatformLog2Es(platformLog);

        // 把日志放进数据库
        // platformLogService.saveLog(platformLog);

        log.info("日志输出: {}", JsonUtil.bean2Json(platformLog));
    }

    /**
     * 根据方法和传入的参数获取请求参数
     *
     * @param method       方法信息
     * @param paramsValues 请求参数值列表
     */
    private Object getParams(Method method, Object[] paramsValues) {
        Map<String, Object> params = new HashMap<>(8);
        // 获取请求参数键集合
        Parameter[] paramsKeys = method.getParameters();

        for (int i = 0; i < paramsKeys.length; i++) {
            // 将RequestBody注解修饰的参数作为请求参数
            RequestBody requestBody = paramsKeys[i].getAnnotation(RequestBody.class);
            if (requestBody != null) {
                return paramsValues[i];
            }

            // 将@RequestParam注解修饰的参数作为请求参数
            RequestParam requestParam = paramsKeys[i].getAnnotation(RequestParam.class);
            if (requestParam != null) {
                assembleParams(paramsKeys, paramsValues, i, requestParam.value(), params);
            }

            // 将@PathVariable注解修饰的参数作为请求参数
            PathVariable pathVariable = paramsKeys[i].getAnnotation(PathVariable.class);
            if (pathVariable != null) {
                assembleParams(paramsKeys, paramsValues, i, pathVariable.value(), params);
            }

            // 将@ModelAttribute注解修饰的参数作为请求参数
            ModelAttribute modelAttribute = paramsKeys[i].getAnnotation(ModelAttribute.class);
            if (modelAttribute != null) {
                assembleParams(paramsKeys, paramsValues, i, modelAttribute.value(), params);
            }

        }

        return params;
    }


    /**
     * TODO：此处需要优化，需要应对不同的参数类型
     * <p>
     * 组装请求参数
     *
     * @param paramsKeys   参数键列表
     * @param paramsValues 参数值列表
     * @param params       最终返回参数列表
     * @param i            遍历计数器
     * @param value        注解标注值，例如@RequestParam(value = "xxx")
     */
    private void assembleParams(Parameter[] paramsKeys, Object[] paramsValues, int i, String value, Map<String, Object> params) {
        String key = paramsKeys[i].getName();
        // 如果注解中设置了键值，则覆盖默认的属性名
        if (StringUtils.isNotBlank(value)) {
            key = value;
        }
        params.put(key, paramsValues[i]);
    }
}
