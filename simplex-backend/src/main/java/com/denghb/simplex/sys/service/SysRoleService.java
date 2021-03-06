package com.denghb.simplex.sys.service;

import com.denghb.simplex.common.model.PageReq;
import com.denghb.simplex.common.model.PageRes;
import com.denghb.simplex.sys.model.req.SysRoleReq;
import com.denghb.simplex.sys.model.res.SysRoleInfoRes;
import com.denghb.simplex.sys.model.res.SysRoleRes;

import java.util.List;

/**
 * @author denghb
 * @since 2019/4/16 23:48
 */
public interface SysRoleService {

    PageRes<SysRoleRes> list(PageReq req);

    void save(SysRoleReq req);

    List<SysRoleInfoRes> list();

    void del(int id);
}
