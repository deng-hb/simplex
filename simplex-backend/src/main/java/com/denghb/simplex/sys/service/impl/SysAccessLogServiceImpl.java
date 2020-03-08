package com.denghb.simplex.sys.service.impl;

import com.denghb.simplex.common.model.PageReq;
import com.denghb.simplex.common.model.PageRes;
import com.denghb.simplex.common.service.impl.EserviceImpl;
import com.denghb.simplex.sys.domain.SysAccessLog;
import com.denghb.simplex.sys.model.res.SysAccessLogRes;
import com.denghb.simplex.sys.service.SysAccessLogService;
import org.springframework.stereotype.Service;

/**
 * @author denghb
 * @since 2019/4/19 23:27
 */
@Service
public class SysAccessLogServiceImpl extends EserviceImpl<SysAccessLog> implements SysAccessLogService {
    @Override
    public PageRes<SysAccessLogRes> list(PageReq req) {
        req.setDefaultDescSort("id");
        String sql = ""/*{
            select * from tb_sys_access_log where deleted = 0
        }*/;
        return selectPage(SysAccessLogRes.class, sql, req);
    }
}
