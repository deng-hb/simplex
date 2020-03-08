package com.denghb.simplex.common.aspect;

import com.denghb.simplex.common.annotation.RequestResubmitLimit;
import com.denghb.simplex.common.base.BizException;
import com.denghb.simplex.common.enums.RedisKey;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * 重复请求提交限制(简单实现)
 */
@Aspect
@Slf4j
@Configuration
public class RequestResubmitLimitAspect {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Around("@annotation(com.denghb.simplex.common.annotation.RequestResubmitLimit)")
    public Object interceptor(ProceedingJoinPoint pjp) {

        String key = null;
        try {
            MethodSignature signature = (MethodSignature) pjp.getSignature();
            Method method = signature.getMethod();
            RequestResubmitLimit ann = method.getAnnotation(RequestResubmitLimit.class);

            key = buildKey(ann, pjp.getArgs(), method.getParameterAnnotations());
            if (null != key) {
                String value = redisTemplate.opsForValue().get(key);
                if (null != value) {
                    throw new BizException("请勿重复提交");
                }
                redisTemplate.opsForValue().set(key, "", ann.expire(), TimeUnit.SECONDS);
            }
            return pjp.proceed();
        } catch (Throwable e) {
            if (e instanceof RuntimeException) {
                throw (RuntimeException) e;
            }
            throw new RuntimeException(e);
        } finally {
            if (null != key) {
                redisTemplate.delete(key);
            }
        }
    }

    /**
     * @param ann
     * @param args
     * @param annotations 参数注解，1维是参数，2维是注解
     * @return
     */
    private String buildKey(RequestResubmitLimit ann, Object[] args, Annotation[][] annotations) {
        String[] fields = ann.fields();
        if (fields.length == 0 || args.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            Object val = args[i];

            if (val instanceof HttpServletRequest || val instanceof HttpServletResponse
                    || val instanceof MultipartFile) {
                continue;
            }

            for (String field : fields) {

                Annotation[] anns = annotations[i];
                for (Annotation a : anns) {
                    if (a instanceof RequestParam) {
                        RequestParam rq = (RequestParam) a;
                        if (field.equals(rq.name()) || field.equals(rq.value())) {
                            sb.append(field);
                            sb.append(":");
                            sb.append(val);
                            sb.append(";");
                        }
                    } else if (a instanceof PathVariable) {
                        PathVariable pv = (PathVariable) a;
                        if (field.equals(pv.name()) || field.equals(pv.value())) {
                            sb.append(field);
                            sb.append(":");
                            sb.append(val);
                            sb.append(";");
                        }
                    }
                }
                // Model
                try {
                    Object v = FieldUtils.readField(val, field, true);
                    sb.append(field);
                    sb.append(":");
                    sb.append(v);
                    sb.append(";");
                } catch (Exception e) {
                    //
                }
            }
        }
        return sb.length() > 0 ? RedisKey.RESUBMIT + ann.name() + DigestUtils.md5Hex(sb.toString()) : null;
    }
}
