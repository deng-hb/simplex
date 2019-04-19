package com.denghb.simplex.sys.service;

import com.denghb.simplex.model.PageReq;
import com.denghb.simplex.model.PageRes;
import com.denghb.simplex.sys.model.res.SysAccessLogRes;

/**
 * @Auther: denghb
 * @Date: 2019/4/19 23:26
 */
public interface SysAccessLogService {

    PageRes<SysAccessLogRes> list(PageReq req);
}
