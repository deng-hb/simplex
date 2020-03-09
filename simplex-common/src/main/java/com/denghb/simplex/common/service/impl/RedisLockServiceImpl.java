package com.denghb.simplex.common.service.impl;

import com.denghb.simplex.common.service.RedisLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Arrays;

@Service
public class RedisLockServiceImpl implements RedisLockService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    @Qualifier("lockScript")
    private RedisScript<Boolean> lockScript;

    private final static String VALUE = "1";

    @Override
    public boolean lock(String key, int expire) {
        Assert.isTrue(expire > 0, "expire > 0");
        return stringRedisTemplate.execute(lockScript, Arrays.asList(key), VALUE, String.valueOf(expire));
    }

    @Override
    public void unlock(String key) {
        stringRedisTemplate.delete(key);
    }

    @Bean(name = "lockScript")
    public RedisScript<Boolean> lockScript() {
        String script = ""/*{
            -- 过期时间秒
            local expire = tonumber(ARGV[2])
            -- 存在的set返回false，成功的返回table: xxxxx..
            local ret = redis.call('set', KEYS[1], ARGV[1], 'NX', 'EX', expire)
            if tostring(ret) == 'false' then
                return false
            else
                return true
            end
        }*/;
        return new DefaultRedisScript<Boolean>(script, Boolean.class);
    }

}
