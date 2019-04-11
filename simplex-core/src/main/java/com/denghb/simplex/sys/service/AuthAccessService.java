package com.denghb.simplex.sys.service;

import com.denghb.simplex.base.Credential;
import com.denghb.simplex.sys.model.SysAccessLogReq;
import org.springframework.cache.annotation.Cacheable;

public interface AuthAccessService {

    int addLog(SysAccessLogReq req);

    void setEndTime(int id);

    @Cacheable(cacheNames = "AuthAccessService.isOpened", key = "'AuthAccessService.isOpened#' + #method + #uri")
    boolean isOpened(String method, String uri);

    Credential validate(String accessToken, String ip, String userAgent);

}
