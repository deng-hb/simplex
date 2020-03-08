package com.denghb.simplex.sys.service;

import com.denghb.simplex.common.base.AuthException;
import com.denghb.simplex.common.holder.Credential;
import com.denghb.simplex.sys.model.req.SysAccessLogReq;

public interface AuthAccessService {

    int addLog(SysAccessLogReq req);

    void setEndTime(int id);

    boolean isOpened(String method, String uri);

    Credential validate() throws AuthException;
}
