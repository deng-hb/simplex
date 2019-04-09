package com.denghb.simplex.base;

public class CredentialContext {

    private static final ThreadLocal<Credential> local = new InheritableThreadLocal<>();

    /**
     * 移除
     */
    public static void remove() {
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
