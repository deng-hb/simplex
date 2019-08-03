package com.denghb.simplex.holder;

public class RequestInfoContextHolder {

    private static final ThreadLocal<RequestInfo> local = new InheritableThreadLocal<>();


    /**
     * 重置
     */
    public static void reset() {
        local.remove();
    }

    /**
     * 设置
     *
     * @param requestInfo
     */
    public static void set(RequestInfo requestInfo) {
        local.set(requestInfo);
    }

    /**
     * 获取
     *
     * @return RequestInfo
     */
    public static RequestInfo get() {
        return local.get();
    }
}
