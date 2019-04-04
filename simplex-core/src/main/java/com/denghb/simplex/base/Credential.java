package com.denghb.simplex.base;

public class Credential {

    private static final ThreadLocal<Credential> local = new InheritableThreadLocal<>();

    private Integer id;

    private String name;

    private String username;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
