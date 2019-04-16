package com.denghb.simplex.sys.service;

import com.denghb.simplex.model.IdReq;
import com.denghb.simplex.model.PageReq;
import com.denghb.simplex.model.PageRes;
import com.denghb.simplex.sys.model.res.SysMenuRes;
import com.denghb.simplex.sys.model.req.SysUserReq;
import com.denghb.simplex.sys.model.res.SysUserRes;
import com.denghb.simplex.sys.model.res.SysUserSignInRes;

import java.util.List;

public interface SysUserService {

    void save(SysUserReq req);

    void del(IdReq req);

    PageRes<SysUserRes> list(PageReq req);

    SysUserSignInRes signIn(String username, String password, String ip, String userAgent);

    void unlockSignError(IdReq req);

    List<SysMenuRes> menu(int sysUserId);

}
