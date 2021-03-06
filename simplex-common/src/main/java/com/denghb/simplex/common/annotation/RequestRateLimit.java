package com.denghb.simplex.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 请求速度限制
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestRateLimit {

    /**
     * 名称（唯一）
     */
    String name();

    /**
     * 最大访问次数
     */
    int max();

    /**
     * 时间段(秒)
     */
    int period() default 1;

    /**
     * 限制IP
     */
    boolean ip() default false;
}
