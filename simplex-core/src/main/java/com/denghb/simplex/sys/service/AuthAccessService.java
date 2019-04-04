package com.denghb.simplex.sys.service;

import com.denghb.simplex.base.Credential;
import org.springframework.cache.annotation.Cacheable;

public interface AuthAccessService {

    @Cacheable(cacheNames = "auth-access-open", key = "#method + #uri")
    boolean isOpened(String method, String uri);

    Credential validate(String accessToken, String ip, String userAgent);

}
