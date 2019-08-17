package com.denghb.simplex.sys.service;

import com.denghb.simplex.base.SysException;
import com.denghb.simplex.holder.Credential;
import com.denghb.simplex.holder.RequestInfo;
import com.denghb.simplex.service.Eservice;
import com.denghb.simplex.sys.model.req.SysAccessLogReq;
import org.springframework.cache.annotation.Cacheable;

public interface AuthAccessService {

    int addLog(SysAccessLogReq req);

    void setEndTime(int id);

    boolean isOpened(String method, String uri);

    Credential validate() throws SysException;
}
