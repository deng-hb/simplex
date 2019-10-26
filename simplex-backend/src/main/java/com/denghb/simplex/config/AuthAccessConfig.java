package com.denghb.simplex.config;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.denghb.simplex.holder.Credential;
import com.denghb.simplex.holder.CredentialContextHolder;
import com.denghb.simplex.holder.RequestInfo;
import com.denghb.simplex.holder.RequestInfoContextHolder;
import com.denghb.simplex.sys.model.req.SysAccessLogReq;
import com.denghb.simplex.sys.service.AuthAccessService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 授权控制
 */
@Component
@Aspect
public class AuthAccessConfig {

    @Autowired
    private AuthAccessService authAccessService;

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object requestMapping(ProceedingJoinPoint joinPoint) throws Throwable {

        return doExec(joinPoint);
    }

    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public Object getMapping(ProceedingJoinPoint joinPoint) throws Throwable {

        return doExec(joinPoint);
    }

    @Around("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public Object postMapping(ProceedingJoinPoint joinPoint) throws Throwable {

        return doExec(joinPoint);
    }

    private Object doExec(ProceedingJoinPoint joinPoint) throws Throwable {

        try {
            RequestInfo requestInfo = RequestInfoContextHolder.get();
            Credential credential = authAccessService.validate();
            CredentialContextHolder.set(credential);

            SysAccessLogReq logReq = SysAccessLogReq.builder()
                    .ip(requestInfo.getIp())
                    .url(requestInfo.getUri())
                    .method(requestInfo.getMethod())
                    .userAgent(requestInfo.getUserAgent())
                    .accessToken(requestInfo.getAccessToken())
                    .build();
            int id = authAccessService.addLog(logReq);

            Object[] args = joinPoint.getArgs();
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            Logger log = LoggerFactory.getLogger(method.getDeclaringClass());

            // join arguments.
            log.info("ReqId:{},{}({})", requestInfo.getReqId(), method.getName(), StringUtils.join(args, ","));
            // 执行方法
            Object result = joinPoint.proceed();
            log.info("ReqId:{},response:{}", requestInfo.getReqId(), JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));
            authAccessService.setEndTime(id);
            return result;
        } finally {
            CredentialContextHolder.reset();
        }

    }
}
