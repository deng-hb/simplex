package com.denghb.simplex.sys.service;

import com.denghb.simplex.holder.Credential;
import com.denghb.simplex.holder.RequestInfo;
import com.denghb.simplex.sys.model.SysAccessLogReq;
import org.springframework.cache.annotation.Cacheable;

public interface AuthAccessService {

    int addLog(SysAccessLogReq req);

    void setEndTime(int id);

    @Cacheable(cacheNames = "AuthAccessService.isOpened", key = "'AuthAccessService.isOpened#' + #method + #uri")
    boolean isOpened(String method, String uri);

    @Cacheable(cacheNames = "AuthAccessService.validate", key = "'AuthAccessService.validate#' + #requestInfo.accessToken + #requestInfo.ip + #requestInfo.uri")
    Credential validate(RequestInfo requestInfo);

}
