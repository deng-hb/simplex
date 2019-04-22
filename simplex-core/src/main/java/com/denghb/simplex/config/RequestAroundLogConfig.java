package com.denghb.simplex.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.denghb.simplex.holder.RequestInfo;
import com.denghb.simplex.holder.RequestInfoContextHolder;
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
public class RequestAroundLogConfig {

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

        RequestInfo requestInfo = RequestInfoContextHolder.get();
        Long reqId = null != requestInfo ? requestInfo.getReqId() : null;
        // join arguments.
        log.info("ReqId:{},params:{}", reqId, StringUtils.join(args, ","));
        Object result = joinPoint.proceed();
        log.info("ReqId:{},response:{}", reqId, JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));

        return result;
    }
}