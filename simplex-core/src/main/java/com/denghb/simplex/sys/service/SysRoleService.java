package com.denghb.simplex.sys.service;

import com.denghb.simplex.model.PageReq;
import com.denghb.simplex.model.PageRes;
import com.denghb.simplex.sys.model.req.SysRoleReq;
import com.denghb.simplex.sys.model.res.SysRoleInfoRes;
import com.denghb.simplex.sys.model.res.SysRoleRes;

import java.util.List;

/**
 * @Auther: denghb
 * @Date: 2019/4/16 23:48
 */
public interface SysRoleService {

    void save(SysRoleReq req);

    PageRes<SysRoleRes> list(PageReq req);

    void del(int id);

    void setSysResourceIds(int roleId, List<Integer> sysResourceIds);

    List<Integer> listSysResourceId(int roleId);

    List<SysRoleInfoRes> list();
}
