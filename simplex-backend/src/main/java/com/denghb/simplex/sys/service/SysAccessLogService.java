package com.denghb.simplex.sys.service;

import com.denghb.simplex.common.model.PageReq;
import com.denghb.simplex.common.model.PageRes;
import com.denghb.simplex.common.service.Eservice;
import com.denghb.simplex.sys.domain.SysAccessLog;
import com.denghb.simplex.sys.model.res.SysAccessLogRes;

/**
 * @author denghb
 * @since 2019/4/19 23:26
 */
public interface SysAccessLogService extends Eservice<SysAccessLog> {

    PageRes<SysAccessLogRes> list(PageReq req);
}
