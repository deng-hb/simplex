package com.denghb.simplex.base;

public class RequestCountContext {

    private static final ThreadLocal<Long> local = new InheritableThreadLocal<>();


    /**
     * 移除
     */
    public static void remove() {
        local.remove();
    }

    /**
     * 设置
     *
     * @param count
     */
    public static void set(Long count) {
        local.set(count);
    }

    /**
     * 获取
     *
     * @return Credential
     */
    public static Long get() {
        return local.get();
    }
}
