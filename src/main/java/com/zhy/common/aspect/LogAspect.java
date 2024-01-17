package com.zhy.common.aspect;

import com.zhy.common.annotation.LogAnnotation;
import com.zhy.common.util.HttpContextUtils;
import com.zhy.common.util.IpUtils;
import com.zhy.entity.Log;
import com.zhy.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 基于Spring AOP的日志切面（Aspect），用于记录系统中指定方法的操作日志。
 *
 * @author zhy
 * <p>
 * 2020.11.03
 */
@Aspect
@Component
public class LogAspect {

    @Autowired
    private LogService logService;

    @Pointcut("@annotation(com.zhy.common.annotation.LogAnnotation)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //保存日志
        saveLog(point, time);
        return result;
    }

    //通过反射获取目标方法上的 @LogAnnotation 注解，提取模块和操作信息。然后获取目标方法的
    // 类名和方法名，请求的 IP 地址，执行时长等信息，并构造一个 Log 对象。最后，
    // 调用 logService.saveLog(log) 方法将日志保存到数据库。
    private void saveLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log log = new Log();
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);

        if (log != null) {
            log.setModule(logAnnotation.module());
            log.setOperation(logAnnotation.operation());
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        log.setMethod(className + "." + methodName + "()");

        //获取request 设置IP地址
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        log.setIp(IpUtils.getIpAddr(request));

        log.setTime(time);
        log.setCreateDate(new Date());

        logService.saveLog(log);
    }

}
