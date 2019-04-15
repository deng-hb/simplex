package com.denghb.simplex.sys.service;

import com.denghb.simplex.sys.model.SysApiReq;
import com.denghb.simplex.sys.model.SysApiRes;

import java.util.List;

/**
 * @Auther: denghb
 * @Date: 2019/4/15 22:04
 */
public interface SysApiService {

    void save(SysApiReq req);

    List<SysApiRes> list();
}
