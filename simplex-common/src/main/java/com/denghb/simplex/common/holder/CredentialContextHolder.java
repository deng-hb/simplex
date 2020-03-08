package com.denghb.simplex.common.holder;

/**
 * @see com.denghb.simplex.common.config.AuthAccessFilter
 */
public class CredentialContextHolder {

    private static final ThreadLocal<Credential> local = new InheritableThreadLocal<>();

    /**
     * 移除
     */
    public static void reset() {
        local.remove();
    }

    /**
     * 设置
     *
     * @param credential
     */
    public static void set(Credential credential) {
        local.set(credential);
    }

    /**
     * 获取
     *
     * @return Credential
     */
    public static Credential get() {
        return local.get();
    }
}
