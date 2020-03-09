package com.denghb.simplex.common.service;

public interface RedisLockService {

    /**
     * 加锁
     *
     * @param key    key
     * @param expire 过期时间（秒）
     * @return boolean
     */
    boolean lock(String key, int expire);

    /**
     * 解锁
     *
     * @param key key
     */
    void unlock(String key);
}
