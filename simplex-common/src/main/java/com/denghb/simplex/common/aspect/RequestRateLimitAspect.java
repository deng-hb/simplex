package com.denghb.simplex.common.aspect;

import com.denghb.simplex.common.base.BizException;
import com.denghb.simplex.common.annotation.RequestRateLimit;
import com.denghb.simplex.common.enums.RedisKey;
import com.denghb.simplex.common.holder.RequestInfo;
import com.denghb.simplex.common.holder.RequestInfoContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * 请求速度限制(简单实现)
 */
@Aspect
@Slf4j
@Configuration
public class RequestRateLimitAspect {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Around("@annotation(com.denghb.simplex.common.annotation.RequestRateLimit)")
    public Object interceptor(ProceedingJoinPoint pjp) {

        try {
            MethodSignature signature = (MethodSignature) pjp.getSignature();
            Method method = signature.getMethod();
            RequestRateLimit ann = method.getAnnotation(RequestRateLimit.class);

            String ip = "";
            if (ann.ip()) {
                RequestInfo info = RequestInfoContextHolder.get();
                ip = info.getIp();
            }

            String key = RedisKey.RATE + ann.name() + ip + System.currentTimeMillis() / (ann.period() * 1000L);
            String value = redisTemplate.opsForValue().get(key);
            int count = StringUtils.isNumeric(value) ? Integer.parseInt(value) : 0;

            if (count >= ann.count()) {
                throw new BizException("请求过于频繁");
            }
            redisTemplate.opsForValue().set(key, String.valueOf(++count), ann.period(), TimeUnit.SECONDS);

            return pjp.proceed();
        } catch (Throwable e) {
            if (e instanceof RuntimeException) {
                throw (RuntimeException) e;
            }
            throw new RuntimeException(e);
        }
    }
}
