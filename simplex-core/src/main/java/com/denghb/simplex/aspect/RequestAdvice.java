package com.denghb.simplex.aspect;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.denghb.simplex.base.RequestCountContext;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class RequestAdvice {

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object requestMapping(ProceedingJoinPoint joinPoint) throws Throwable {

        return doExe(joinPoint);
    }

    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public Object getMapping(ProceedingJoinPoint joinPoint) throws Throwable {

        return doExe(joinPoint);
    }

    @Around("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public Object postMapping(ProceedingJoinPoint joinPoint) throws Throwable {

        return doExe(joinPoint);
    }

    private Object doExe(ProceedingJoinPoint joinPoint) throws Throwable {

        Object args[] = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Logger log = LoggerFactory.getLogger(method.getDeclaringClass());
        // join arguments.
        log.info("req:{},params:{} ", RequestCountContext.get(), StringUtils.join(args, " ; "));
        Object result = joinPoint.proceed();
        log.info("req:{},res:{} ", RequestCountContext.get(), JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue));

        return result;
    }
}