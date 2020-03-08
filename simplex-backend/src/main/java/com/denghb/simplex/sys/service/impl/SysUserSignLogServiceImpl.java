package com.denghb.simplex.sys.service.impl;

import com.denghb.eorm.Eorm;
import com.denghb.simplex.sys.domain.SysUserSignLog;
import com.denghb.simplex.sys.model.req.SysUserSignLogReq;
import com.denghb.simplex.sys.service.SysUserSignLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author denghb
 * @since 2019/4/13 17:24
 */
@Service
public class SysUserSignLogServiceImpl implements SysUserSignLogService {

    @Autowired
    private Eorm db;

    @Override
    public int add(SysUserSignLogReq req) {
        SysUserSignLog sysUserSignLog = new SysUserSignLog();
        BeanUtils.copyProperties(req, sysUserSignLog);
        db.insert(sysUserSignLog);
        return sysUserSignLog.getId();
    }

    @Override
    public void setSuccess(int id) {
        SysUserSignLog sysUserSignLog = new SysUserSignLog();
        sysUserSignLog.setStatus(1);
        sysUserSignLog.setId(id);
        db.update(sysUserSignLog);
    }
}
