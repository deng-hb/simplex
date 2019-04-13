package com.denghb.simplex.sys.service;

import com.denghb.simplex.model.IdsReq;
import com.denghb.simplex.sys.model.SysRoleMenuReq;

import java.util.List;

/**
 * @Auther: denghb
 * @Date: 2019/4/13 22:54
 */
public interface SysRoleMenuService {

    void save(List<SysRoleMenuReq> list);

    void dels(List<IdsReq> list);


}
