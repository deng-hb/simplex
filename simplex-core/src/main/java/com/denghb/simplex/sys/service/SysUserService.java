package com.denghb.simplex.sys.service;

import com.denghb.simplex.sys.model.SysUserReq;
import com.denghb.simplex.sys.model.SysUserSignInRes;

public interface SysUserService {

    void save(SysUserReq req);

    SysUserSignInRes signIn(String username, String password);
}
