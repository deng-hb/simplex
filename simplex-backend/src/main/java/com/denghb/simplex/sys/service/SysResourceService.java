package com.denghb.simplex.sys.service;

import com.denghb.simplex.sys.model.req.SysResourceQueryReq;
import com.denghb.simplex.sys.model.req.SysResourceReq;
import com.denghb.simplex.sys.model.res.SysResourceRes;

import java.util.List;

/**
 * @author denghb
 * @since 2019/4/13 21:51
 */
public interface SysResourceService {

    void save(SysResourceReq req);

    void del(int id);

    List<SysResourceRes> list(SysResourceQueryReq req);
}
