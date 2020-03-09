package com.denghb.simplex.common.service.impl;


import com.denghb.simplex.common.model.CaptchaRes;
import com.denghb.simplex.common.service.CaptchaService;
import com.denghb.simplex.common.utils.CaptchaUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class CaptchaServiceImpl implements CaptchaService {

    private final static String REDIS_KEY_CAPTCHA = "captcha#";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public CaptchaRes generate() {
        try {
            CaptchaUtils.CaptchaResult result = CaptchaUtils.generate();

            String key = UUID.randomUUID().toString();

            CaptchaRes res = new CaptchaRes();
            res.setImageData(result.getImageData());
            res.setKey(key);

            // 5分钟有效
            stringRedisTemplate.boundValueOps(REDIS_KEY_CAPTCHA + key).set(result.getCode(), 5, TimeUnit.MINUTES);
            return res;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public boolean validate(String key, String code) {
        String rkey = REDIS_KEY_CAPTCHA + key;
        String oldCode = stringRedisTemplate.boundValueOps(rkey).get();
        stringRedisTemplate.delete(rkey);
        return null != oldCode && oldCode.equalsIgnoreCase(code);
    }
}
