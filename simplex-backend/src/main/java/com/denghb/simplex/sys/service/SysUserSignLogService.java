package com.denghb.simplex.sys.service;

import com.denghb.simplex.sys.model.req.SysUserSignLogReq;

/**
 * @author denghb
 * @since 2019/4/13 17:22
 */
public interface SysUserSignLogService {

    int add(SysUserSignLogReq req);

    void setSuccess(int id);
}
