package com.denghb.simplex.common.aspect;

import com.denghb.simplex.common.base.BizException;
import com.denghb.simplex.common.annotation.RequestRateLimit;
import com.denghb.simplex.common.enums.RedisKey;
import com.denghb.simplex.common.holder.RequestInfo;
import com.denghb.simplex.common.holder.RequestInfoContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 请求速度限制(简单实现)
 */
@Aspect
@Slf4j
@Configuration
public class RequestRateLimitAspect {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    @Qualifier("rateLimitScript")
    private RedisScript<Boolean> rateLimitScript;

    @Around("@annotation(com.denghb.simplex.common.annotation.RequestRateLimit)")
    public Object interceptor(ProceedingJoinPoint pjp) {

        try {
            MethodSignature signature = (MethodSignature) pjp.getSignature();
            Method method = signature.getMethod();
            RequestRateLimit ann = method.getAnnotation(RequestRateLimit.class);

            String key = RedisKey.RATE + ann.name();
            if (ann.ip()) {
                RequestInfo info = RequestInfoContextHolder.get();
                key += info.getIp();
            }

            Assert.isTrue(ann.period() > 0, "period > 0");
            Boolean next = stringRedisTemplate.execute(rateLimitScript, Arrays.asList(key), String.valueOf(ann.max()), String.valueOf(ann.period()));
            if (!next) {
                log.info("RequestRateLimit:{}", ann);
                throw new BizException("请求过于频繁");
            }

            return pjp.proceed();
        } catch (Throwable e) {
            if (e instanceof RuntimeException) {
                throw (RuntimeException) e;
            }
            throw new RuntimeException(e);
        }
    }

    @Bean(name = "rateLimitScript")
    public RedisScript<Boolean> rateLimitScript() {

        String script = ""/*{
            -- 限流key lua 下标从 1 开始
            local key = KEYS[1]
            -- 限流大小
            local limit = tonumber(ARGV[1])

            -- 获取当前流量大小
            local currentLimit = tonumber(redis.call('get', key) or "0")
            if currentLimit + 1 > limit then
                -- 达到限流大小 返回
                return false
            else
                -- 没有达到阈值 value + 1
                redis.call('INCRBY', key, 1)
                -- EXPIRE后边的单位是秒
                redis.call('EXPIRE', key, tonumber(ARGV[2]))
                return true
            end
        }*/;

        return new DefaultRedisScript<Boolean>(script, Boolean.class);
    }
}
