package com.denghb.simplex.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 短时间重复请求提交限制
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestResubmitLimit {

    /**
     * 名称（唯一）
     */
    String name();

    /**
     * 过期秒数,默认为5秒，超过5秒会自动解锁
     */
    int expire() default 5;

    /**
     * 参数名称
     */
    String[] fields();
}
