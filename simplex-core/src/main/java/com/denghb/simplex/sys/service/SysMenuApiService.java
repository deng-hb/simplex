package com.denghb.simplex.sys.service;

import com.denghb.simplex.model.IdReq;
import com.denghb.simplex.sys.model.SysApiRes;

import java.util.List;

/**
 * @Auther: denghb
 * @Date: 2019/4/13 21:51
 */
public interface SysMenuApiService {

    void save(int menuId, List<IdReq> reqList);

    void del(int menuId, List<IdReq> reqList);

    List<SysApiRes> list(int menuId);
}
